package app.timetracker_service;

import app.timetrack_repository.IActivityRepository;
import app.timetrack_repository.IEmployeeRepository;
import app.timetrack_repository.IProjectRepository;
import app.timetracker_dto_implementation.ActivityDto;
import app.timetracker_dto_implementation.BulkActivityDto;
import app.timetracker_dto_implementation.FailedActivityDto;
import app.timetracker_entity_implemantion.Activity;
import app.timetracker_entity_implemantion.Employee;
import app.timetracker_entity_implemantion.Project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    private final IActivityRepository activityRepository;
    private final IEmployeeRepository employeeRepository;
    private final IProjectRepository projectRepository;

    public ActivityService(IActivityRepository activityRepository, IEmployeeRepository employeeRepository,IProjectRepository projectRepository) {
        this.activityRepository = activityRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    public BulkActivityDto bulkInsert(List<ActivityDto> requests) {

        List<Integer> savedIds = new ArrayList<>();
        List<FailedActivityDto> failedRecords = new ArrayList<>();

        for (ActivityDto dto : requests) {

            String validationError = validate(dto);
            if (validationError != null) {
                failedRecords.add(new FailedActivityDto(dto, validationError));
                continue;
            }

            try {
                Employee employee = employeeRepository.findById(dto.getEmployeeId())
                        .orElseThrow(() -> new RuntimeException("Employee not found"));
                Project project = projectRepository.findById(dto.getProjectId())
                        .orElseThrow(() -> new RuntimeException("Project not found"));

                Activity activity = new Activity();
                activity.setEmployee(employee);
                activity.setProject(project);
                activity.setDescription(dto.getDescription());
                activity.setTimeOfActivity(dto.getTime());

                Activity saved = activityRepository.save(activity);
                savedIds.add(saved.getId());

            } catch (Exception e) {
                failedRecords.add(new FailedActivityDto(dto, "Database error: " + e.getMessage()));
            }
        }

        return new BulkActivityDto(savedIds, failedRecords);
    }

    private String validate(ActivityDto dto) {

        if (dto.getEmployeeId() == null) return "EmployeeId is mandatory";
        if (dto.getProjectId() == null) return "ProjectId is mandatory";
        if (dto.getDescription() == null || dto.getDescription().isBlank()) return "Description is mandatory";
        if (dto.getTime() == null) return "Time is mandatory";
        if (!"Orion".equals(dto.getEmail())) return "Email must be Orion";

        return null;
    }

}
