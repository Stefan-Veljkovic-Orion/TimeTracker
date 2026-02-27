import json
import random
from datetime import datetime, timedelta, timezone
from pathlib import Path
from typing import List, Dict, Any

from .api_client import ApiClient
from models.Employee import Employee


def load_names() -> List[str]:
    path = Path(__file__).parent / "data_set" / "names.json"
    data = json.loads(path.read_text(encoding="utf-8"))
    names = [str(n).strip() for n in data if str(n).strip()]
    if not names:
        raise ValueError("No names found in names.json")
    return names


def generate_email(name: str, existing_emails: set) -> str:
    parts = name.lower().split()
    base = f"{parts[0]}.{parts[-1]}@orion.com"
    email = base
    counter = 1
    while email in existing_emails:
        email = f"{parts[0]}.{parts[-1]}{counter}@orion.com"
        counter += 1
    return email


def random_employment_date(years_back: int = 10) -> datetime:
    end = datetime.now(timezone.utc)
    start = end - timedelta(days=years_back * 365)
    delta_seconds = int((end - start).total_seconds())
    return start + timedelta(seconds=random.randint(0, delta_seconds))


def generate_employees(num: int = 5) -> List[Dict[str, Any]]:
    names = load_names()
    client = ApiClient()

    departments = client.get_departments()
    if not departments:
        raise ValueError("No departments returned from backend")

    existing_employees = client.get_employees()
    existing_emails = {e.get("email", "") for e in existing_employees}
    existing_names = {e.get("name", "") for e in existing_employees}

    available_names = [n for n in names if n not in existing_names]
    if len(available_names) < num:
        raise ValueError(
            f"Not enough unique names: need {num}, have {len(available_names)} unused"
        )

    employees = []
    for name in random.sample(available_names, num):
        email = generate_email(name, existing_emails)
        existing_emails.add(email)

        dept = random.choice(departments)
        employee = Employee(
            name=name,
            email=email,
            date_of_employment=random_employment_date(),
            department_id=int(dept.get("id")),
        )
        employees.append(employee.to_dict())

    return employees


def generate_and_send_employees(num: int = 5) -> int:
    employees = generate_employees(num)
    client = ApiClient()
    return client.create_employees(employees)


def run_generate_employees_cli() -> None:
    try:
        num = int(input("How many employees to generate? (default 5): ") or "5")
        inserted = generate_and_send_employees(num)
        print(f"AI generated and inserted: {inserted}")
    except Exception as e:
        print(f"AI generation failed: {e}")
