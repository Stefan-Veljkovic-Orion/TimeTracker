from utils.storage import load_data, save_locally, EMPLOYEES_FILE
from utils.validators import validate_employee
import json
from models import Employee

def create_employee():
    print("\n--- Create New Employee ---")
    name = input("Enter employee name: ")
    email = input("Enter employee email: ")
    date_of_employment = input("Enter date of employment (e.g. YYYY-MM-DD): ") # DODATO POLJE
    # BEZBEDAN UNOS ZA DEPARTMENT (Sprečava pucanje programa)
    department_input = input("Enter department ID (number): ")
    if not department_input.isdigit():
        print("Validation error: Department must be a valid number (ID).")
        return # Prekida ako korisnik nije ukucao broj
    
    employee_input = input("Enter employee ID (number): ")

    if not employee_input.isdigit():
        print("Validation error: Employee must be a valid number (ID).")
        return # Prekida ako korisnik nije ukucao broj
    
    project_input = input("Enter project ID (number): ")
    if not project_input.isdigit():
        print("Validation error: Employee must be a valid number (ID).")
        return # Prekida ako korisnik nije ukucao broj

    department = int(department_input)
    
    # 1. Osnovna validacija (ime i domen)
    if not validate_employee(name, email):
        return
        
    # 2. Učitavamo postojeće zaposlene
    employees = load_data(EMPLOYEES_FILE)
    
    # 3. DODATA PROVERA ZA DUPLIKATE: Da li email već postoji u sistemu?
    for emp in employees:
        if emp["email"].lower() == email.lower():
            print(f"Error: Employee with email {email} already exists!")
            return # Prekidamo dodavanje
            
    # 4. Ako je sve u redu, dodajemo u listu i čuvamo
    employees.append({
        "name": name, 
        "email": email, 
        "date_of_employment": date_of_employment, # Dodato u JSON
        "department": department
    })
    
    save_locally(EMPLOYEES_FILE, employees)
    print(f"Success! Employee {name} ({email}) created successfully.")


