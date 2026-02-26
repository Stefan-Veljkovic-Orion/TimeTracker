package app.timeTracker_controller;


import app.timetrack_service.ProjectService;
import app.timetracker_dto_implementation.ProjectDto;
import app.timetracker_entity_implemantion.Project;
import app.timetracker_mapper.ProjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectService projectService,
                             ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    // CREATE
    @PostMapping
    public ProjectDto createProject(@Valid @RequestBody ProjectDto dto) {
        Project project = projectMapper.toEntity(dto);
        Project saved = projectService.saveProject(project);
        return projectMapper.toDto(saved);
    }

    // GET ALL
    @GetMapping
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects()
                .stream()
                .map(projectMapper::toDto)
                .toList();
    }


    // GET BY ID
    @GetMapping("/{id}")
    public ProjectDto getProjectById(@PathVariable int id) {
        return projectMapper.toDto(projectService.getProjectById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ProjectDto updateProject(@PathVariable int id,
                                    @Valid @RequestBody ProjectDto dto) {
        Project project = projectMapper.toEntity(dto);
        Project updated = projectService.updateProject(id, project);
        return projectMapper.toDto(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
    }

    //EXPORT CSV
    @GetMapping(value = "/export-csv", produces = "text/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=project.csv");
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] header = {"ID", "Project Name", "Description", "Manager Email"};
        String[] fieldMapping = {"id", "projectName", "description", "managerEmail"};
        csvWriter.writeHeader(header);
        for (Project e : projectService.getAllProjects()) {
            csvWriter.write(e, fieldMapping);
        }

        csvWriter.close();
    }

}
