package app.timetrack_service;

import app.timetrack_repository.IActivityRepository;
import app.timetrack_repository.IProjectRepository;
import app.timetracker_dto_implementation.*;
import app.timetracker_entity_implemantion.Project;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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

        public Project saveProject (Project project){

            return projectRepository.save(project);
        }

        public BulkProjectDto bulkInsert (List < ProjectDto > requests) {
            List<Integer> savedIds = new ArrayList<>();
            List<FailedProjectDto> failedRecords = new ArrayList<>();

            for (ProjectDto dto : requests) {

                String validationError = validate(dto);
                if (validationError != null) {
                    failedRecords.add(new FailedProjectDto(validationError, dto));
                    continue;
                }

                try {

                    Project project = new Project();
                    project.setProjectName(dto.getProjectName());
                    project.setDescription(dto.getDescription());
                    project.setManagerEmail(dto.getManagerEmail());
                    Project saved = projectRepository.save(project);
                    savedIds.add(saved.getId());

                } catch (Exception e) {
                    failedRecords.add(new FailedProjectDto("Database error: " + e.getMessage(), dto));
                }
            }

            return new BulkProjectDto(savedIds, failedRecords);
        }

        private String validate (ProjectDto dto){
            if (dto.getProjectName() == null || dto.getProjectName().isBlank()) return "Project name is mandatory.";
            if (dto.getDescription() == null || dto.getDescription().isBlank()) return "Description is mandatory.";
            if (dto.getDescription().length() > 150) return "Description can be at most 150 characters.";
            if (dto.getManagerEmail() == null || dto.getManagerEmail().isBlank()) return "Manager email is mandatory.";
            if (!dto.getManagerEmail().toLowerCase().endsWith("@orion.com"))
                return "Manager email must be an Orion email (@orion.com).";
            return null;
        }

        public List<Project> getAllProjects () {
            return projectRepository.findAll();
        }

        public Project getProjectById ( int id){
            return projectRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Project not found."));
        }

        public Project updateProject ( int id, Project updatedProject){
            Project project = getProjectById(id);
            project.setProjectName(updatedProject.getProjectName());
            project.setDescription(updatedProject.getDescription());
            project.setManagerEmail(updatedProject.getManagerEmail());
            return projectRepository.save(project);
        }

        public void deleteProject ( int id){

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
