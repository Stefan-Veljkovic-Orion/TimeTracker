def validate_employee(name, email):
    if len(name.strip().split()) < 2:
        print("Validation error: Employee name must include first and last name(s).")
        return False
    if not email.lower().endswith("@orion.com"):
        print("Validation error: Employee email must be an Orion domain (@orion.com).")
        return False
    return True

def validate_activity(email, description, project, time_of_activity):
    if not email or not description or not project or not time_of_activity:
        print("Validation error: Activity must have email, project, and time.")
        return False
    if "@orion.com" not in email.lower():
        print("Validation error: Employee email must be Orion domain.")
        return False
    return True

def validate_project(name, description, manager_email):
    if not name:
        print("Validation error: Project must have a name.")
        return False
    if not description or len(description) > 150:
        print("Validation error: Project description required and max 150 chars.")
        return False
    if "@orion.com" not in manager_email.lower():
        print("Validation error: Manager email must be Orion domain.")
        return False
    return True