package app.timetracker_entity_implemantion;

import jakarta.persistence.*;
import app.timetracker_entity.MyEntity;

import java.util.Objects;

@Entity
@Table(name="department")
public class Department{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String departmentName;
    private String contactEmail;
    private String description;

    public Department(){
        
    }

    public Department(String contactEmail, String description, String departmentName, int id) {
        this.contactEmail = contactEmail;
        this.description = description;
        this.departmentName = departmentName;
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Department that)) return false;
        return id == that.id && Objects.equals(departmentName, that.departmentName) && Objects.equals(contactEmail, that.contactEmail) && Objects.equals(description, that.description);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
