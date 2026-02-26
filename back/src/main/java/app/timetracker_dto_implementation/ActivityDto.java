package app.timetracker_dto_implementation;


import app.timetracker_entity_implemantion.Employee;
import app.timetracker_entity_implemantion.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ActivityDto {

    private Employee employee;
    private Project project;
    @NotNull(message = "EmployeeId is mandatory")
    private Integer employeeId;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotNull(message = "ProjectId is mandatory")
    private Integer projectId;
    @NotNull(message = "Time is mandatory")

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

    public Integer getEmployeeId() { return employeeId; }

    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public Integer getProjectId() { return projectId; }

    public void setProjectId(Integer projectId) { this.projectId = projectId; }
}
