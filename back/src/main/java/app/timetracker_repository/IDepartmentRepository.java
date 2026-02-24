package app.timetracker_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.timetracker_entity_implemantion.Department;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department,Integer> {
}
