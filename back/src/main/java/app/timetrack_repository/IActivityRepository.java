package app.timetrack_repository;

import app.timetracker_entity_implemantion.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActivityRepository extends JpaRepository<Activity,Integer> {
    boolean existsByEmployee_Id(int id);
}
