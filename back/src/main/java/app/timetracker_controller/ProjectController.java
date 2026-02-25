package app.timeTracker_controller;


import app.timetrack_service.ProjectService;
import app.timetracker_dto_implementation.ProjectDto;
import app.timetracker_entity_implemantion.Project;
import app.timetracker_mapper.ProjectMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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


    // UPDATE
    @PutMapping("/{id}")
    public ProjectDto updateProject(@PathVariable int id,
                                    @Valid @RequestBody ProjectDto dto) {
        Project project = projectMapper.toEntity(dto);
        Project updated = projectService.updateProject(id, project);
        return projectMapper.toDto(updated);
    }

}
