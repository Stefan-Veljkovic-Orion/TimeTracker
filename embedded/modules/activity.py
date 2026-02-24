from utils.storage import load_data, save_data, ACTIVITIES_FILE
from utils.validators import validate_activity

# Privremene aktivnosti u memoriji
activities_temp = []

def create_activity():
    employee = input("Enter employee name: ")
    project = input("Enter project name: ")
    description = input("Enter activity description: ")
    time_of_activity = input("Enter time of activity (HH:MM): ")
    
    if not validate_activity(description, project, time_of_activity):
        return
        
    activities_temp.append({
        "employee": employee,
        "project": project,
        "description": description,
        "time": time_of_activity
    })
    print(f"Activity for {employee} on project {project} recorded (not yet stored).")

def store_activities():
    global activities_temp
    if not activities_temp:
        print("No new activities to store.")
        return
        
    activities = load_data(ACTIVITIES_FILE)
    activities.extend(activities_temp)
    save_data(ACTIVITIES_FILE, activities)
    activities_temp.clear()
    print("Activities stored to DB (JSON).")

