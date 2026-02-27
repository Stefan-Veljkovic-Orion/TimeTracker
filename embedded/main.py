# Uvozimo funkcije iz naših modula
from modules.activity import create_activity, print_activities_on_console
from modules.employee import create_employee
from modules.project import create_project
from modules.sync_export import sync_employees, export_employees_to_csv
from modules.tracker import track_new_activities
from utils.storage import store_activities_to_db
import threading
import uvicorn
import time as time_module
from ai.server import app as ai_app
from ai.generate_activities import run_generate_activities_cli
from ai.generate_employees import run_generate_employees_cli


def main_menu():
    config = uvicorn.Config(ai_app, host="0.0.0.0", port=8001, log_level="info")
    server = uvicorn.Server(config)

    threading.Thread(target=server.run, daemon=True).start()

    while not server.started:
        time_module.sleep(0.01)

    while True:
        print("\n--- Time Tracker: Embedded Application ---")
        print("1. Create Activity")
        print("2. Create Employee")
        print("3. Create Project")
        print("4. Store activities on DB")
        print("5. Track new activities today")
        print("6. Sync Employees with backend")
        print("7. [AI] Generate activities")
        print("8. [AI] Generate employees")
        print("9. Exit")

        choice = input("Choose an option: ")

        if choice == "1":
            create_activity()
        elif choice == "2":
            create_employee()
        elif choice == "3":
            create_project()
        elif choice == "4":
            store_activities_to_db()
        elif choice == "5":
            track_new_activities()
            print_activities_on_console()
        elif choice == "6":
            sync_employees()
            export_employees_to_csv()
        elif choice == "7":
            run_generate_activities_cli()
        elif choice == "8":
            run_generate_employees_cli()
        elif choice == "9":
            print("Exiting...")
            break
        else:
            print("Invalid choice, try again.")


if __name__ == "__main__":
    main_menu()