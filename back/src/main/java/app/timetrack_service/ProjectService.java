package app.timetrack_service;

import app.timetrack_repository.IProjectRepository;
import app.timetracker_entity_implemantion.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final IProjectRepository projectRepository;
    public ProjectService(IProjectRepository projectRepository) {

        this.projectRepository = projectRepository;
    }

    public Project saveProject(Project project){

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found."));
    }

    public Project updateProject(int id, Project updatedProject) {
        Project project = getProjectById(id);
        project.setProjectName(updatedProject.getProjectName());
        project.setDescription(updatedProject.getDescription());
        project.setManagerEmail(updatedProject.getManagerEmail());
        return projectRepository.save(project);
    }


}
