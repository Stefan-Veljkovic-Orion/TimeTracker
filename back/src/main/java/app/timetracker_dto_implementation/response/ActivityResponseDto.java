package app.timetracker_dto_implementation.response;

import app.timetracker_entity_implemantion.Activity;
import app.timetracker_entity_implemantion.Employee;
import app.timetracker_entity_implemantion.Project;

import java.time.LocalDateTime;

public class ActivityResponseDto {

    private Integer id;
    private String description;
    private LocalDateTime time;

    // Simple employee (includes departmentName and dateOfEmployment via Employee -> Department)
    private EmployeeResponseDtoSimple employee;

    // Now using ProjectResponseDto (not the simple one)
    private ProjectResponseDto project;

    public ActivityResponseDto() {}

    public ActivityResponseDto(Integer id,
                               String description,
                               LocalDateTime time,
                               EmployeeResponseDtoSimple employee,
                               ProjectResponseDto project) {
        this.id = id;
        this.description = description;
        this.time = time;
        this.employee = employee;
        this.project = project;
    }

    public static ActivityResponseDto from(Activity a) {
        if (a == null) return null;

        // Build simple Employee DTO with departmentName + dateOfEmployment
        EmployeeResponseDtoSimple employeeDto = null;
        Employee e = a.getEmployee();
        if (e != null) {
            String departmentName = (e.getDepartment() != null) ? e.getDepartment().getDepartmentName() : null;
            employeeDto = new EmployeeResponseDtoSimple(
                    e.getId(),
                    e.getName(),
                    e.getEmail(),
                    departmentName,
                    e.getDateOfEmployment()
            );
        }

        // Build full ProjectResponseDto (no department field on project)
        ProjectResponseDto projectDto = null;
        Project p = a.getProject();
        if (p != null) {
            projectDto = new ProjectResponseDto(
                    p.getId(),
                    p.getProjectName(),
                    p.getDescription(),
                    p.getManagerEmail()
            );
        }

        return new ActivityResponseDto(
                a.getId(),
                a.getDescription(),
                a.getTimeOfActivity(),
                employeeDto,
                projectDto
        );
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public EmployeeResponseDtoSimple getEmployee() { return employee; }
    public void setEmployee(EmployeeResponseDtoSimple employee) { this.employee = employee; }

    public ProjectResponseDto getProject() { return project; }
    public void setProject(ProjectResponseDto project) { this.project = project; }
}