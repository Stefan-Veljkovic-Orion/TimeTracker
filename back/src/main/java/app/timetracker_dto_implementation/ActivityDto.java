package app.timetracker_dto_implementation;


import app.timetracker_entity_implemantion.Employee;
import app.timetracker_entity_implemantion.Project;

import java.time.LocalDateTime;

public class ActivityDto {

    private Integer employeeId;
    private String description;
    private Integer projectId;
    private LocalDateTime time;
    private String email;

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

}

