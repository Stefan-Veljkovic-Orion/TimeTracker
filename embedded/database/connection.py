import mysql.connector
 
cnx = mysql.connector.connect(user='root', password='root',
                              host='127.0.0.1',
                              database='timetracker')
 
import requests
import json
 
# Učitavanje lokalnih aktivnosti (pretpostavka da ih čuvaš u fajlu)
with open('data/activities.json', 'r') as file:
    activities_data = json.load(file)
 
# URL vašeg Back-End API-ja (ovo treba da dogovoriš sa BE timom)
api_url = "http://localhost:8080/activities/bulk-insert"
 
try:
    # Slanje JSON podataka na Bekend putem POST zahteva
    response = requests.post(api_url, json=activities_data)
 
    if response.status_code == 200 or response.status_code == 201:
        print("Aktivnosti uspešno poslate na Back-End!")
    else:
        print(f"Greška pri slanju! Status kod: {response.status_code}")
except requests.exceptions.RequestException as e:
    print(f"Greška u komunikaciji sa API-jem: {e}")