package app.timetracker_entity_implemantion;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="activity")
public class Activity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime timeOfActivity;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

    public Activity(int id, Project project, Employee employee, String description, LocalDateTime timeOfActivity) {
        this.id = id;
        this.project = project;
        this.employee = employee;
        this.description = description;
        this.timeOfActivity = timeOfActivity;
    }

    public Activity(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimeOfActivity() {
        return timeOfActivity;
    }

    public void setTimeOfActivity(LocalDateTime timeOfActivity) {
        this.timeOfActivity = timeOfActivity;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Activity activity)) return false;
        return id == activity.id && Objects.equals(timeOfActivity, activity.timeOfActivity) && Objects.equals(description, activity.description) && Objects.equals(employee, activity.employee) && Objects.equals(project, activity.project);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", timeOfActivity=" + timeOfActivity +
                ", description='" + description + '\'' +
                ", employee=" + employee +
                ", project=" + project +
                '}';
    }

}
