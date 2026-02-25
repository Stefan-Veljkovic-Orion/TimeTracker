package app.timetracker_dto_implementation;


import app.timetracker_entity_implemantion.Employee;
import app.timetracker_entity_implemantion.Project;

import java.time.LocalDateTime;

public class ActivityDto {
    private Employee employee;
    private String description;
    private Project project;
    private LocalDateTime time;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }


}
