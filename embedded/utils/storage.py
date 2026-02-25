import json
import os
from database.connection import cnx
from models.Employee import Employee
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

def save_data(file, data :Employee):
    # Provera da li postoji 'data' folder, ako ne, napravi ga
    os.makedirs(os.path.dirname(file), exist_ok=True)
    with open(file, "w") as f:
        json.dump(data, f, indent=2)
    cursor = cnx.cursor()
    insert_employee = ("insert into employee (date_of_employment, departmentid,email, name) values (?,?,?,?);")
    cursor.execute(insert_employee,(data.date_of_employment, data.departmentid,data.email,data.name))
    try:
        cnx.commit()
    except Exception:
        raise Exception("Eror while saving Employee")
    cnx.close()
