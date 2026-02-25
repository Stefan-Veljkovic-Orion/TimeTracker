package app.timetracker_mapper;

import app.timetracker_dto_implementation.ProjectDto;
import app.timetracker_entity_implemantion.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements DtoEntityMapper<ProjectDto, Project> {

    @Override
    public ProjectDto toDto(Project e) {
        return new ProjectDto(e.getId(), e.getProjectName(), e.getDescription(), e.getManagerEmail());
    }

    @Override
    public Project toEntity(ProjectDto t) {
        return new Project(t.getId(), t.getProjectName(), t.getDescription(), t.getManagerEmail(), null);
    }
}
