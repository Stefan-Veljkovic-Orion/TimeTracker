# Uvozimo funkcije iz naših modula
from modules.activity import create_activity, store_activities, track_new_activities
from modules.employee import create_employee
from modules.project import create_project
from modules.sync_export import sync_employees

def main_menu():
    while True:
        print("\n--- Time Tracker: Embedded Application ---")
        print("1. Create Activity")
        print("2. Create Employee")
        print("3. Create Project")
        print("4. Store activities on DB")
        print("5. Track new activities today")
        print("6. Sync Employees with backend")
        print("7. Exit")
 
        choice = input("Choose an option: ")
 
        if choice == "1":
            create_activity()
        elif choice == "2":
            create_employee()
        elif choice == "3":
            create_project()
        elif choice == "4":
            store_activities()
        elif choice == "5":
            track_new_activities()
        elif choice == "6":
            sync_employees()
        elif choice == "7":
            print("Exiting...")
            break
        else:
            print("Invalid choice, try again.")

if __name__ == "__main__":
    main_menu()