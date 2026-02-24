import json
import os

# Konstante za putanje do fajlova
EMPLOYEES_FILE = "data/employees.json"
PROJECTS_FILE = "data/projects.json"
ACTIVITIES_FILE = "data/activities.json"

def load_data(file):
    if os.path.exists(file):
        with open(file, "r") as f:
            return json.load(f)
    return []

def save_data(file, data):
    # Provera da li postoji 'data' folder, ako ne, napravi ga
    os.makedirs(os.path.dirname(file), exist_ok=True)
    with open(file, "w") as f:
        json.dump(data, f, indent=2)