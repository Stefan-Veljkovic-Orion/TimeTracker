package app.timetracker_controller;


import app.timetrack_service.ProjectService;
import app.timetracker_dto_implementation.ProjectDto;
import app.timetracker_entity_implemantion.Project;
import app.timetracker_mapper.ProjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // 🔹 CREATE
    @PostMapping
    public ProjectDto createProject(@RequestBody ProjectDto dto) {
        Project project = projectMapper.toEntity(dto);
        Project saved = projectService.saveProject(project);
        return projectMapper.toDto(saved);
    }


}
