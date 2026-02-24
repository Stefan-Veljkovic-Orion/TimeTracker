package app.timetracker_dto_implementation;

import app.timetracker_dto.Dto;

public class DepartmentDto {

    private int id;
    private String departmentName;
    private String contactEmail;
    private String description;

    public DepartmentDto() {
    }

    public DepartmentDto(int id, String departmentName, String contactEmail, String description) {
        this.id = id;
        this.departmentName = departmentName;
        this.contactEmail = contactEmail;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
