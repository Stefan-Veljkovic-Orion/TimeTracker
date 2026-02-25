package app.timetracker_dto_implementation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.List;

public class EmpolyeeDto {

    private int id;

    @NotEmpty(message = "Name required")
    private String name;

    @NotEmpty(message = "Email required")
    @Email(message = "Email format is not valid")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@orion\\.com$",
            message = "Email must be in the orion.com domain"
    )
    private String email;

    private LocalDateTime dateOfEmployment;

    @NotNull(message = "Department required")
    private Integer departmentID;

    private List<ActivityDto> activities;

    public EmpolyeeDto() {
    }

    public EmpolyeeDto(int id, String name, String email, LocalDateTime dateOfEmployment, Integer departmentID) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfEmployment = dateOfEmployment;
        this.departmentID = departmentID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ActivityDto> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityDto> activities) {
        this.activities = activities;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public LocalDateTime getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDateTime dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}