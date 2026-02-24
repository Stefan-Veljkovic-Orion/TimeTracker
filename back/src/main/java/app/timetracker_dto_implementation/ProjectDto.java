package app.timetracker_dto_implementation;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ProjectDto {

    private int id;
    private String projectName;
    @NotEmpty(message = "Name is required.")
    private String description;
    @Size(max = 150, message = "Decription can be at most 150 characters.")
    private String managerEmail;

    public ProjectDto() {
    }

    public ProjectDto(int id, String projectName, String description, String managerEmail) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.managerEmail = managerEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

}
