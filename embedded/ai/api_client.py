import requests
from typing import List, Dict, Any


class ApiClient:
    def __init__(self, base_url: str = "http://localhost:8080"):
        self.base_url = base_url.rstrip("/")
        self.session = requests.Session()

    def get_employees(self) -> List[Dict[str, Any]]:
        url = f"{self.base_url}/employees"
        resp = self.session.get(url, timeout=10)
        resp.raise_for_status()
        return resp.json()

    def get_projects(self) -> List[Dict[str, Any]]:
        url = f"{self.base_url}/projects"
        resp = self.session.get(url, timeout=10)
        resp.raise_for_status()
        return resp.json()

    def get_departments(self) -> List[Dict[str, Any]]:
        url = f"{self.base_url}/departments"
        resp = self.session.get(url, timeout=10)
        resp.raise_for_status()
        return resp.json()

    def create_employee(self, employee: Dict[str, Any]) -> bool:
        url = f"{self.base_url}/employees"
        resp = self.session.post(url, json=employee, timeout=10)
        resp.raise_for_status()
        return True


    def create_employees(self, employees: List[Dict[str, Any]]) -> int:
        inserted = 0
        for employee in employees:
            try:
                self.create_employee(employee)
                inserted += 1
            except Exception as e:
                print(f"⚠️  Failed to insert employee: {e}")
                continue
        print(f"✅ Inserted {inserted}/{len(employees)} employees")
        return inserted

    def create_activity(self, activity: Dict[str, Any]) -> bool:
        url = f"{self.base_url}/activities/create"
        resp = self.session.post(url, json=activity, timeout=10)
        resp.raise_for_status()
        return True

    def create_activities(self, activities: List[Dict[str, Any]]) -> int:
        inserted = 0
        for activity in activities:
            try:
                self.create_activity(activity)
                inserted += 1
            except Exception as e:
                print(f"⚠️  Failed to insert activity: {e}")
                continue
        print(f"✅ Inserted {inserted}/{len(activities)} activities")
        return inserted
