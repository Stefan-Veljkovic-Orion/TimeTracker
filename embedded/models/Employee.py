from dataclasses import dataclass
from datetime import datetime


@dataclass
class Employee:
    name: str
    email: str
    date_of_employment: datetime
    department_id: int

    def to_dict(self) -> dict:
        return {
            "name": self.name,
            "email": self.email,
            "dateOfEmployment": self.date_of_employment.strftime("%Y-%m-%dT%H:%M:%S"),
            "department": {
                "id": self.department_id
            },
        }
