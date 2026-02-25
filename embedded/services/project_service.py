from models.Project import Project
import requests

def store_data(projectList: list[Project]):
    requests.post("http://localhost:8080/createAll",projectList)