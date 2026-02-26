package app.timetracker_controller;


import app.timetrack_service.ProjectService;
import app.timetracker_dto_implementation.BulkProjectDto;
import app.timetracker_dto_implementation.ProjectDto;
import app.timetracker_entity_implemantion.Project;
import app.timetracker_mapper.ProjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
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
    
    //bulk insert
    @PostMapping(value = "/bulk-insert",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BulkProjectDto> bulkInsert(@RequestBody List<ProjectDto> projects) {
        BulkProjectDto response = projectService.bulkInsert(projects);
        return ResponseEntity.ok(response);
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


}
