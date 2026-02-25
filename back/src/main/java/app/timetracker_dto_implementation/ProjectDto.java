package app.timetracker_dto_implementation;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProjectDto {

    private int id;

    @NotBlank(message = "Name is required.")
    private String projectName;

    @Size(max = 150, message = "Decription can be at most 150 characters.")
    private String description;

    @NotBlank(message = "Manager email is required")
    @Email(message = "Manager email must be a valid email.")
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
