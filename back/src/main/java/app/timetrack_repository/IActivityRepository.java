package app.timetrack_repository;

import app.timetracker_entity_implemantion.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActivityRepository extends JpaRepository<Activity,Integer> {
}
