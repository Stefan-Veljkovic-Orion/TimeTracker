package app.timetrack_service;

import app.timetrack_repository.IActivityRepository;
import app.timetrack_repository.IProjectRepository;
import app.timetracker_entity_implemantion.Project;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class ProjectService {

    private final IProjectRepository projectRepository;
    private final IActivityRepository activityRepository;
    public ProjectService(IProjectRepository projectRepository, IActivityRepository activityRepository) {

        this.projectRepository = projectRepository;
        this.activityRepository = activityRepository;
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

    public void deleteProject(int id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));

        if (activityRepository.existsByProjectId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Can't delete project because it has activities linked to it.");
        }

        projectRepository.delete(project);
    }

    public void exportProjectsToCsv(Writer writer) throws IOException {

        List<Project> projects = projectRepository.findAll();

        try (ICsvBeanWriter csvWriter =
                     new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE)) {

            String[] header = {"id", "projectName", "description", "managerEmail"};
            csvWriter.writeHeader(header);

            for (Project project : projects) {
                csvWriter.write(project, header);
            }
        }
    }

}
