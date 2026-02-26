import json
import os
from database.connection import cnx
from models.Activity import Activity
import requests
# Konstante za putanje do fajlova
EMPLOYEES_FILE = "data/employees.json"
PROJECTS_FILE = "data/projects.json"
ACTIVITIES_FILE = "data/activities.json"

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

from datetime import datetime
from utils.storage import load_data, save_locally, ACTIVITIES_FILE

def store_activities_to_db():
    print("\n--- Storing activities to DB via API ---")
    
    activities = load_data(ACTIVITIES_FILE)
    
    if not activities:
        print("Nema novih lokalnih aktivnosti za slanje na backend.")
        return
        
    api_url = "http://localhost:8080/activities/bulk-insert" 
    

    try:
        print(f"Slanje {len(activities)} formatiranih aktivnosti na server...")
        
        
        response = requests.post(api_url, json=activities)
        
        if response.status_code == 200 or response.status_code == 201:
            print("[+] Uspešno sačuvano na Back-End!")
            save_locally(ACTIVITIES_FILE, []) 
        else:
            print(f"[-] Greška od strane servera! Status kod: {response.status_code}")
            print(f"Detalji greške: {response.text}")
            
    except requests.exceptions.RequestException as e:
        print("[-] Nije moguće povezati se sa API-jem.")

    