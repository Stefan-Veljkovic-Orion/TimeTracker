package app.timetrack_service;

import app.timetrack_repository.IProjectRepository;
import app.timetracker_entity_implemantion.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final IProjectRepository projectRepository;
    public ProjectService(IProjectRepository projectRepository) {

        this.projectRepository = projectRepository;
    }

    public Project saveProject(Project project){

        return projectRepository.save(project);
    }




}
