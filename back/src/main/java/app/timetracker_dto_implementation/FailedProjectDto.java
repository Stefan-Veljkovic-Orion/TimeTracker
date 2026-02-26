package app.timetracker_dto_implementation;

import app.timetracker_entity_implemantion.Project;

public class FailedProjectDto {
    public ProjectDto project;
    public String reason;

    public FailedProjectDto(){
        
    }

    public FailedProjectDto(String reason, ProjectDto project) {
        this.reason = reason;
        this.project = project;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
