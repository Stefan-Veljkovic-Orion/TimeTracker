package app.timetracker_dto_implementation;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ActivityDto {

    @JsonProperty(value = "employee")
    private EmployeeDto employee;
    @JsonProperty(value = "project")
    private ProjectDto project;
    @NotNull(message = "EmployeeId is mandatory")
    @JsonProperty(value = "employee_id")
    private Integer employeeId;
    @JsonProperty(value = "description")
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotNull(message = "ProjectId is mandatory")
    @JsonProperty(value = "project_id")
    private Integer projectId;
    @NotNull(message = "Time is mandatory")
    private LocalDateTime time;

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }


    
}
