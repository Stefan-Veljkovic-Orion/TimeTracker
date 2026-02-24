package app.timetrack_repository;

import app.timetracker_entity_implemantion.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department,Integer> {
}
