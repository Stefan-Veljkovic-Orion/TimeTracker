from utils.storage import load_data, save_locally, ACTIVITIES_FILE
from utils.validators import validate_activity
from models.Activity import Activity

def create_activity():
    email = input("Enter employee mail: ")
    # TODO da li je u redu da se uzima naziv projekta ili je potrebno project_id?
    project = input("Enter project name: ")
    description = input("Enter activity description: ")
    # TODO u bazi je datetime a mi primamo samo HH:MM
    time_of_activity = input("Enter time of activity (HH:MM): ")
    
    if not validate_activity(email, description, project, time_of_activity):
        return
        
    activity = {
        "email": email,
        "project": project,
        "description": description,
        "time": time_of_activity
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