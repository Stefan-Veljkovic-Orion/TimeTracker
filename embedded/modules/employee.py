from utils.storage import load_data, save_locally, EMPLOYEES_FILE
from utils.validators import validate_employee
from models.Employee import Employee
import json
from services.employee_service import store_data

def create_employee():
    print("\n--- Create New Employee ---")
    name = input("Enter employee name: ")
    email = input("Enter employee email: ")
    date_of_employment = input("Enter date of employment (e.g. YYYY-MM-DD): ") # DODATO POLJE
    department = int(input("Enter department: "))
    
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

def load_employees():
    with open("./../data/employees.json",'r', encoding='UTF-8') as file:
        employeeList: list[Employee] = json.load(file)
        store_data(employeeList)
