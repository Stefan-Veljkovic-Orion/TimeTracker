package app.timetracker_dto_implementation;

import java.time.LocalDateTime;

public class EmployeeCSVDto {
    private Integer id;
    private String name;
    private String email;
    private LocalDateTime dateOfEmployment;
    private String departmentName;

    public EmployeeCSVDto(Integer id, String name, String email, LocalDateTime dateOfEmployment, String departmentName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfEmployment = dateOfEmployment;
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDateTime dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
