package app.timetracker_dto_implementation;

import java.time.LocalDateTime;

public class ActivityCSVDto {
    private Integer id;
    private String employeeName;
    private String projectName;
    private String description;
    private LocalDateTime timeOfActivity;

    public ActivityCSVDto(Integer id, String employeeName,
                          String projectName,
                          String description,
                          LocalDateTime timeOfActivity) {
        this.id = id;
        this.employeeName = employeeName;
        this.projectName = projectName;
        this.description = description;
        this.timeOfActivity = timeOfActivity;
    }

    public Integer getId() { return id; }
    public String getEmployeeName() { return employeeName; }
    public String getProjectName() { return projectName; }
    public String getDescription() { return description; }
    public LocalDateTime getTimeOfActivity() { return timeOfActivity; }
}
