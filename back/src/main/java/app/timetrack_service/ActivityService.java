package app.timetrack_service;

import app.timetrack_repository.IActivityRepository;
import app.timetrack_repository.IEmployeeRepository;
import app.timetrack_repository.IProjectRepository;
import app.timetracker_dto_implementation.csv.ActivityCSVDto;
import app.timetracker_dto_implementation.ActivityDto;
import app.timetracker_dto_implementation.bulk.BulkActivityDto;
import app.timetracker_dto_implementation.bulk.FailedActivityDto;
import app.timetracker_entity_implemantion.Activity;
import app.timetracker_entity_implemantion.Employee;
import app.timetracker_entity_implemantion.Project;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ActivityCSVDto> getAllActivity(){
        List<Activity> lista = activityRepository.findAll();
        return  lista.stream().map(a -> new ActivityCSVDto(a.getId(), a.getEmployee().getName(), a.getProject().getProjectName(),a.getDescription(),a.getTimeOfActivity())).toList();
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
                Employee employee = dto.getEmployee();

                Project project = dto.getProject();


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

    @Transactional
    public Activity create(ActivityDto dto) {
        Project project = projectRepository.findById(dto.getProject().getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project not found: " + dto.getProject().getId()));

        Employee employee = employeeRepository.findById(dto.getEmployee().getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employee not found: " + dto.getEmployee().getId()));

        Activity entity = new Activity();
        entity.setProject(project);
        entity.setEmployee(employee);
        entity.setDescription(dto.getDescription());
        entity.setTimeOfActivity(dto.getTime());

        try {
            return activityRepository.save(entity);
        } catch (DataIntegrityViolationException e) {
            // Unique/FK constraint errors -> 409 Conflict
            e.getMostSpecificCause();
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Data conflict: " + e.getMostSpecificCause().getMessage(),
                    e
            );
        }
    }



    public List<Activity> findActivitiesForEmployee(Integer employeeId, String fromDate, String toDate) {
        if (employeeId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "employeeId is required");
        }
        LocalDateTime from;
        LocalDateTime to;

        if (fromDate != null && !fromDate.isBlank()) {
            from = LocalDate.parse(fromDate).atTime(LocalTime.MIN);
        } else {
            from = null;
        }
        if (toDate != null && !toDate.isBlank()) {
            to = LocalDate.parse(toDate).atTime(LocalTime.MAX);
        } else {
            to = null;
        }

        List<Activity> activities;

        try {
            activities = activityRepository.findByEmployeeId(employeeId,from,to);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to load activities", ex);
        }

        return activities.stream()
                .filter(a -> {
                    LocalDateTime t = a.getTimeOfActivity();
                    if (t == null) return false;
                    boolean afterFrom = (from == null) || !t.isBefore(from);
                    boolean beforeTo = (to == null) || !t.isAfter(to);
                    return afterFrom && beforeTo;
                })
                .sorted(Comparator.comparing(Activity::getTimeOfActivity).reversed())
                .collect(Collectors.toList());
    }

    private String validate(ActivityDto dto) {

        if (dto.getEmployee() == null) return "EmployeeId is mandatory";
        if (dto.getProject() == null) return "ProjectId is mandatory";
        if (dto.getDescription() == null || dto.getDescription().isBlank()) return "Description is mandatory";
        if (dto.getTime() == null) return "Time is mandatory";
        return null;
    }

}
