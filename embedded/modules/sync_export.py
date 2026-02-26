import csv
import requests
from datetime import datetime
from utils.storage import load_data, EMPLOYEES_FILE


import csv
import requests
from datetime import datetime
from utils.storage import load_data, save_locally, EMPLOYEES_FILE 

def sync_employees():
    print("\n--- Syncing Employees with Backend ---")
    
    # 1. Učitavamo lokalne radnike
    local_employees = load_data(EMPLOYEES_FILE)
    
    # Pravimo listu postojećih mejlova da ne bismo ubacili istog radnika dvaput
    local_emails = {emp.get("email", "").lower() for emp in local_employees}
    
    try:
        # 2. Povlačimo radnike sa baze
        response = requests.get("http://localhost:8080/activities/from-last-five-seconds")
        
        if response.status_code == 200:
            db_employees = response.json()
            
            added_count = 0
            # 3. Dodajemo one iz baze u našu lokalnu listu (ako već nisu tu)
            for db_emp in db_employees:
                email = db_emp.get("email", "").lower()
                
                if email not in local_emails:
                    standardizovan_radnik = {
                        "name": db_emp.get("name"),
                        "email": db_emp.get("email"),
                        # Formatiramo datum ako želimo da odstranimo vreme (opciono, ali lepo)
                        "date_of_employment": str(db_emp.get("date_of_employment")).split(" ")[0], 
                        
                        
                        "department": db_emp.get("departmentid") 
                    }
                    
                    # Sada dodajemo standardizovanog radnika, a ne sirovog iz baze
                    local_employees.append(standardizovan_radnik)
                    local_emails.add(email)
                    added_count += 1
            
            # 4. ČUVAMO SPOJENU LISTU NAZAD U LOKALNI JSON
            save_locally(EMPLOYEES_FILE, local_employees)
            print(f"[+] Sinhronizacija uspešna! Dodato {added_count} novih radnika iz baze.")
            
        else:
            print(f"[-] Server je vratio grešku: {response.status_code}")
            
    except Exception as e:
        print(f"[-] Greška pri konekciji sa API-jem: {e}")


def export_employees_to_csv():
    employees = load_data(EMPLOYEES_FILE)
    if not employees:
        print("No employees to export.")
        return
        
    timestamp = datetime.now().strftime("%Y-%m-%d_%H-%M-%S")  
    filename = f"employees_{timestamp}.csv"
    
    with open(filename, "w", newline="") as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=["name", "email", "date_of_employment", "department"])
        writer.writeheader()
        for emp in employees:
            writer.writerow(emp)
            
    print(f"Employees exported to {filename}")