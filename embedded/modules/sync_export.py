import csv
from datetime import datetime
from utils.storage import load_data, EMPLOYEES_FILE

def sync_employees():
    employees = load_data(EMPLOYEES_FILE)
    print(f"Syncing {len(employees)} employees with backend...")
    # Ovde će kasnije ići HTTP POST (requests biblioteka)
    print("Sync completed (stub).")

def export_employees_to_csv():
    employees = load_data(EMPLOYEES_FILE)
    if not employees:
        print("No employees to export.")
        return
        
    timestamp = datetime.now().strftime("%Y-%m-%d_%H-%M-%S")
    filename = f"employees_{timestamp}.csv"
    
    with open(filename, "w", newline="") as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=["name", "email", "department"])
        writer.writeheader()
        for emp in employees:
            writer.writerow(emp)
            
    print(f"Employees exported to {filename}")