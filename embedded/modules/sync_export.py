import csv
import requests
from datetime import datetime
from utils.storage import load_data, EMPLOYEES_FILE


# function for loading employees from the EMPLOYEES_FILE
def sync_employees():
    employees = load_data(EMPLOYEES_FILE)
    if not employees:
        print("No employees to sync")
        return
    api_url=""
    # response=requests.post(api_url, json=employees, headersheaders)

    ''' if response.status_code==200:
    print(f"Synced {len(employees)} employees with backend")
    else:
        print(f"Error syncing employees: {response.status_code}{response.text}")'''
    
# function for exporting data to CSV file
def export_employees_to_csv():
    employees = load_data(EMPLOYEES_FILE)
    if not employees:
        print("No employees to export.")
        return
        
    timestamp = datetime.now().strftime("%Y-%m-%d_%H-%M-%S")  #opening csv file for writing
    filename = f"employees_{timestamp}.csv"
    
    with open(filename, "w", newline="") as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=["name", "email", "department"])
        writer.writeheader()
        for emp in employees:
            writer.writerow(emp)
            
    print(f"Employees exported to {filename}")