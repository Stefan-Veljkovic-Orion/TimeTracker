package app.timetrack_repository;

import app.timetracker_entity_implemantion.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
}
