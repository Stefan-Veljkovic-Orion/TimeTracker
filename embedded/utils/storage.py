import json
import os
from database.connection import cnx
from models.Employee import Employee
from models.Activity import Activity
# Konstante za putanje do fajlova
EMPLOYEES_FILE = "embedded/data/employees.json"
PROJECTS_FILE = "embedded/data/projects.json"
ACTIVITIES_FILE = "embedded/data/activities.json"

def load_data(file):
    if os.path.exists(file):
        print(f"File {file} exists")
        with open(file, "r") as f:
            return json.load(f)
    return []
# TODO save_data prethodno koristen za sve a sada izmenjen samo da radi za Employee
def save_locally(file, data):
    os.makedirs(os.path.dirname(file), exist_ok=True)
    with open(file, "w") as f:
        json.dump(data, f, indent=2)


def save_activity_to_db(file, data :Activity):
    os.makedirs(os.path.dirname(file), exist_ok=True)
    with open(file, "w") as f:
        json.dump(data, f, indent=2)
    cursor = cnx.cursor()
    insert_project = ("insert into activity (email, project, description, time_of_activity) values (?,?,?,?);")
    cursor.execute(insert_project,(data.email, data.project,data.description, data.time_of_activity))
    try:
        cnx.commit()
    except Exception:
        raise Exception("Eror while saving Project")
    cnx.close()



    