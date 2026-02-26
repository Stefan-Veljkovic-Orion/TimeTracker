package app.timetracker_dto_implementation.response;

public class ProjectResponseDto {

    private int id;
    private String projectName;
    private String description;
    private String managerEmail;

    public ProjectResponseDto() {
    }

    public ProjectResponseDto(int id, String projectName, String description, String managerEmail) {
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