package app.timetracker_dto_implementation.response;

import java.time.LocalDateTime;

public class EmployeeResponseDtoSimple {

    private int id;
    private String name;
    private String email;
    private String departmentName;
    private LocalDateTime dateOfEmployment;

    public EmployeeResponseDtoSimple() {
    }

    public EmployeeResponseDtoSimple(int id,
                                     String name,
                                     String email,
                                     String departmentName,
                                     LocalDateTime dateOfEmployment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentName = departmentName;
        this.dateOfEmployment = dateOfEmployment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public LocalDateTime getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDateTime dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }
}