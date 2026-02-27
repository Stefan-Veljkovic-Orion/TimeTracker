import json
import random
from datetime import datetime, timedelta, timezone
from pathlib import Path
from typing import List, Tuple, Dict, Any


from .api_client import ApiClient



def load_descriptions() -> List[str]:
    path = Path(__file__).parent / "data" / "descriptions.json"
    data = json.loads(path.read_text(encoding="utf-8"))
    descriptions = [str(d).strip() for d in data if str(d).strip()]
    if not descriptions:
        raise ValueError("No descriptions found in descriptions.json")
    return descriptions



def random_past_datetime(days_back: int = 365) -> str:
    end = datetime.now(timezone.utc)
    start = end - timedelta(days=days_back)
    delta_seconds = int((end - start).total_seconds())
    dt = start + timedelta(seconds=random.randint(0, delta_seconds))
    return dt.strftime("%Y-%m-%dT%H:%M:%S")



def generate_activities(num: int = 100) -> List[Dict[str, Any]]:
    descriptions = load_descriptions()
    client = ApiClient()

    employees = client.get_employees()
    projects = client.get_projects()

    if not employees:
        raise ValueError("No employees returned from backend")
    if not projects:
        raise ValueError("No projects returned from backend")

    used_keys: set[Tuple[int, str, int, str]] = set()
    activities: List[Dict[str, Any]] = []

    max_attempts = num * 50
    attempts = 0

    while len(activities) < num and attempts < max_attempts:
        attempts += 1

        emp = random.choice(employees)
        proj = random.choice(projects)
        desc = random.choice(descriptions)

        dt = random_past_datetime()
        date_str = dt[:10]

        emp_id = emp.get("id")
        proj_id = proj.get("id")
        if emp_id is None or proj_id is None:
            continue

        key = (int(emp_id), date_str, int(proj_id), desc)
        if key in used_keys:
            continue

        used_keys.add(key)
        activities.append({
            "time": dt,
            "employee": emp,
            "project": proj,
            "employee_id": int(emp_id),
            "project_id": int(proj_id),
            "description": desc,
        })

    if len(activities) < num:
        print(f"Warning: generated only {len(activities)} of {num} activities (exhausted unique combinations)")

    return activities



def generate_and_send_activities(num: int = 5) -> int:
    activities = generate_activities(num)
    client = ApiClient()
    return client.create_activities(activities)



def run_generate_activities_cli() -> None:
    try:
        num = int(input("How many activities to generate? (default 5): ") or "5")
        inserted = generate_and_send_activities(num)
        print(f"AI generated and inserted: {inserted}")
    except Exception as e:
        print(f"AI generation failed: {e}")
