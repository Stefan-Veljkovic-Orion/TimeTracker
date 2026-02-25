from models.Employee import Employee
import requests

def store_data(employeeList: list[Employee]):
    requests.post("http://localhost:8080/createAll",employeeList)