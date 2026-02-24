package app.timetrack_repository;

import app.timetracker_entity_implemantion.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project,Integer> {
}
