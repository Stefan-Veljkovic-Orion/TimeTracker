package app.timetracker_entity_implemantion;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="project")
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String projectName;
    private String managerEmail;
    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
    private List<Activity> activities;

    public Project(int id, String managerEmail, List<Activity> activities, String projectName) {
        this.id = id;
        this.managerEmail = managerEmail;
        this.activities = activities;
        this.projectName = projectName;
    }
    
    public Project(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Project project)) return false;
        return id == project.id && Objects.equals(projectName, project.projectName) && Objects.equals(managerEmail, project.managerEmail) && Objects.equals(activities, project.activities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectName, managerEmail, activities);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", managerEmail='" + managerEmail + '\'' +
                ", activities=" + activities +
                '}';
    }
}
