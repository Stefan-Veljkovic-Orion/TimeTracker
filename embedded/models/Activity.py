from dataclasses import dataclass

@dataclass
class Activity:
    time: str
    employee_id: int
    description: str
    project_id: int