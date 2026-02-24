package app.timetracker_entity_implemantion;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="employee")
public class Employee{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private LocalDateTime dateOfEmployment;
    private int departmentID;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Activity> activities;

    public Employee(){
        
    }

    public Employee(int id, List<Activity> activities, int departmentID, LocalDateTime dateOfEmployment, String email, String name) {
        this.id = id;
        this.activities = activities;
        this.departmentID = departmentID;
        this.dateOfEmployment = dateOfEmployment;
        this.email = email;
        this.name = name;
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

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public LocalDateTime getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDateTime dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee employee)) return false;
        return id == employee.id && departmentID == employee.departmentID && Objects.equals(name, employee.name) && Objects.equals(email, employee.email) && Objects.equals(dateOfEmployment, employee.dateOfEmployment) && Objects.equals(activities, employee.activities);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateOfEmployment=" + dateOfEmployment +
                ", departmentID=" + departmentID +
                ", activities=" + activities +
                '}';
    }
}
