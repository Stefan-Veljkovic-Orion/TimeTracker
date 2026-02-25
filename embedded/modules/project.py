from utils.storage import load_data, save_locally, PROJECTS_FILE
from utils.validators import validate_project



# function for creating a project
def create_project():
    name = input("Enter project name: ")
    description = input("Enter project description (max 150 chars): ")
    manager_email = input("Enter manager email: ")
    
    if not validate_project(name, description, manager_email):
        return
        
    projects = load_data(PROJECTS_FILE)
    projects.append({"name": name, "description": description, "manager_email": manager_email})
    save_locally(PROJECTS_FILE, projects)  
    print(f"Project {name} created with manager {manager_email}!")