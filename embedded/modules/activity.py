from utils.storage import load_data, save_locally, ACTIVITIES_FILE
from utils.validators import validate_activity
from models.Activity import Activity
from datetime import datetime, timezone

def create_activity():
    description = input("Enter activity description: ")
    # TODO u bazi je datetime a mi primamo samo HH:MM
    time_of_activity = datetime.now().isoformat(timespec='seconds')

    employee_input = input("Enter employee ID (number): ")

    if not employee_input.isdigit():
        print("Validation error: Employee must be a valid number (ID).")
        return # Prekida ako korisnik nije ukucao broj
    
    project_input = input("Enter project ID (number): ")

    if not project_input.isdigit():
        print("Validation error: Employee must be a valid number (ID).")
        return # Prekida ako korisnik nije ukucao broj
    
    employee = int(employee_input)
    project = int(project_input)
        
    activity = {
        "description": description,
        "time": time_of_activity,
        "employee_id": employee,
        "project_id": project
    }

    activities = load_data(ACTIVITIES_FILE)
    activities.append(activity)
    save_locally(ACTIVITIES_FILE, activities)

    print("Activity saved locally")


    
def print_activities_on_console():
    activities = load_data(ACTIVITIES_FILE)
    print("\n--- Today's Activities ---")
    if not activities:
        print("No activities yet.")
    for act in activities:
        print(f"{act['time']} - {act['email']} - {act['project']} - {act['description']}")
    print("--------------------------")