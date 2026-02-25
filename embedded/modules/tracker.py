
import time
from datetime import datetime
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

    try:
        # Glavna beskonacna petlja (Polling)
        while True:
            # KADA STIGNE API, OVO MENJAS SA: response = requests.get('...') i activities = response.json()
            activities = load_data(ACTIVITIES_FILE)

            # Prolazimo kroz sve dobijene aktivnosti
            for act in activities:
                # (Kada BE napravi API, trazi da ti salju polje "id" pa ce ovo biti: unique_id = act['id'])
                unique_id = f"{act.get('employee')}_{act.get('time')}_{act.get('project')}"
                
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