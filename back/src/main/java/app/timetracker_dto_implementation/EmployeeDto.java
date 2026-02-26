package app.timetracker_dto_implementation;

import app.timetracker_entity_implemantion.Activity;
import app.timetracker_entity_implemantion.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.List;

public class EmployeeDto {

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

    @JsonIgnore
    private Department department;

    @JsonIgnore
    private List<Activity> activities;

    public EmployeeDto() {
    }

    public EmployeeDto(int id, String name, String email, LocalDateTime dateOfEmployment, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfEmployment = dateOfEmployment;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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