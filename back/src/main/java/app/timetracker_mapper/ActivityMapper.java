package app.timetracker_mapper;

import app.timetracker_dto_implementation.ActivityDto;
import app.timetracker_dto_implementation.ActivityResponseDto;
import app.timetracker_dto_implementation.EmployeeResponseDtoSimple;
import app.timetracker_dto_implementation.ProjectResponseDto;
import app.timetracker_entity_implemantion.Activity;
import app.timetracker_entity_implemantion.Employee;
import app.timetracker_entity_implemantion.Project;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper implements DtoEntityMapper<ActivityDto, Activity> {

    /**
     * Map JPA entity -> request DTO (ActivityDto).
     */
    @Override
    public ActivityDto toDto(Activity activity) {
        if (activity == null) return null;

        ActivityDto dto = new ActivityDto();
        dto.setDescription(activity.getDescription());
        dto.setTime(activity.getTimeOfActivity());

        if (activity.getEmployee() != null) {
            dto.setEmployee(activity.getEmployee());
        }
        if (activity.getProject() != null) {
            dto.setProject(activity.getProject());
        }

        return dto;
    }

    /**
     * Map request DTO -> JPA entity (partial).
     * IMPORTANT: Do NOT set 'employee' and 'project' here because they require DB lookups.
     * Let the service fetch Employee/Project and set them on the entity.
     */
    @Override
    public Activity toEntity(ActivityDto dto) {
        if (dto == null) return null;

        Activity activity = new Activity();
        activity.setDescription(dto.getDescription());
        activity.setTimeOfActivity(dto.getTime());
        // employee/project are set in the service after repository lookups
        return activity;
    }

    /**
     * Map JPA entity -> response DTO used by API output.
     * Builds:
     * - EmployeeResponseDtoSimple (id, name, email, departmentName via Employee->Department, dateOfEmployment)
     * - ProjectResponseDto (id, projectName, description, managerEmail)
     */
    public ActivityResponseDto toResponse(Activity activity) {
        if (activity == null) return null;

        ActivityResponseDto res = new ActivityResponseDto();
        res.setId(activity.getId());
        res.setDescription(activity.getDescription());
        res.setTime(activity.getTimeOfActivity());

        // Employee (simple)
        EmployeeResponseDtoSimple employeeDto = null;
        Employee e = activity.getEmployee();
        if (e != null) {
            String departmentName = (e.getDepartment() != null) ? e.getDepartment().getDepartmentName() : null;
            employeeDto = new EmployeeResponseDtoSimple(
                    e.getId(),
                    e.getName(),
                    e.getEmail(),
                    departmentName,
                    e.getDateOfEmployment()
            );
        }
        res.setEmployee(employeeDto);

        // Project (full response dto without department)
        ProjectResponseDto projectDto = null;
        Project p = activity.getProject();
        if (p != null) {
            projectDto = new ProjectResponseDto(
                    p.getId(),
                    p.getProjectName(),
                    p.getDescription(),
                    p.getManagerEmail()
            );
        }
        res.setProject(projectDto);

        return res;
    }
}