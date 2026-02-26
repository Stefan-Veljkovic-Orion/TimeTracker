
import time
from datetime import datetime

import requests
from utils.storage import load_data, ACTIVITIES_FILE

def track_new_activities():
    print("\nStarting Activity Tracker")
    print("Prekini pracenje pritiskom na Ctrl+C (ili Cmd+C na Mac-u)")
    
    # 1. Odredjivanje imena fajla na osnovu vremena pokretanja komande
    start_time = datetime.now()
    timestamp_str = start_time.strftime("%Y-%m-%d_%H-%M-%S")
    filename = f"report_{timestamp_str}.txt"
    
    # Otvaramo/kreiramo fajl i ispisujemo zaglavlje
    with open(filename, "w") as f:
        f.write(f" Activity Report started at {start_time.strftime('%Y-%m-%d %H:%M:%S')}\n\n")

    # Skup (Set) u koji smestamo jedinstvene identifikatore vidjenih aktivnosti
    seen_activities = set()

    # Učitavamo sve postojeće aktivnosti pre petlje da ih ne bismo prijavili kao nove
    initial_activities = load_data(ACTIVITIES_FILE)
    for act in initial_activities:
        unique_id = f"{act.get('employee')}_{act.get('time')}_{act.get('project')}"
        seen_activities.add(unique_id)

    print(f"[Info] Pronadjeno {len(seen_activities)} postojecih aktivnosti. Cekanje novih\n")

    try:
        # Glavna beskonacna petlja (Polling)
        while True:
           
            try:
                response = requests.get("http://localhost:8080/activities")
                activities = response.json()
            except Exception as e:
                print(f"Greška pri konekciji sa API-jem: {e}")
                return

            # Prolazimo kroz sve dobijene aktivnosti
            for act in activities:
                
                unique_id = act['id']
                
                # Ako je ID nov (nismo ga do sada videli)
                if unique_id not in seen_activities:
                    # Dodajemo ga u skup vidjenih
                    seen_activities.add(unique_id)
                    
                    # Formatiranje detalja po zahtevu taska
                    details = f"[NOVA AKTIVNOST] {act.get('time')} | {act.get('employee')} na '{act.get('project')}': {act.get('description')}"
                    
                    # 1. Prikaz u terminalu (jednu po jednu)
                    print(details)
                    
                    # 2. Upis u tekstualni log fajl (mod 'a' za append)
                    with open(filename, "a", encoding="utf-8") as f:
                        f.write(details + "\n")
            
            # Pauza od 5 sekundi
            time.sleep(5)

    except KeyboardInterrupt:
        print(f"\n[!] Pracenje zaustavljeno. Izvestaj novih aktivnosti je sacuvan u fajlu: {filename}")
        print("Povratak u glavni meni")