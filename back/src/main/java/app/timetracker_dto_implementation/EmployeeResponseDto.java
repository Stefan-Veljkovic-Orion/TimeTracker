package app.timetracker_dto_implementation;

import java.time.LocalDateTime;

public class EmployeeResponseDto {

    private int id;
    private String name;
    private String email;
    private LocalDateTime dateOfEmployment;

    // Full department info (not just ID)
    private DepartmentResponseDto department;

    public EmployeeResponseDto() {
    }

    public EmployeeResponseDto(int id, String name, String email,
                               LocalDateTime dateOfEmployment,
                               DepartmentResponseDto department) {
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

    public DepartmentResponseDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentResponseDto department) {
        this.department = department;
    }
}