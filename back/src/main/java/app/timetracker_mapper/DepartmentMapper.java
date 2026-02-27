package app.timetracker_mapper;

import app.timetracker_dto_implementation.DepartmentDto;
import app.timetracker_dto_implementation.response.DepartmentResponseDto;
import app.timetracker_entity_implemantion.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper implements DtoEntityMapper<DepartmentDto, Department> {

    @Override
    public DepartmentDto toDto(Department department) {
        if (department == null) return null;

        DepartmentDto dto = new DepartmentDto();
        dto.setDepartmentName(department.getDepartmentName());
        dto.setContactEmail(department.getContactEmail());
        dto.setDescription(department.getDescription());
        return dto;
    }

    @Override
    public Department toEntity(DepartmentDto dto) {
        if (dto == null) return null;

        Department department = new Department();
        department.setDepartmentName(dto.getDepartmentName());
        department.setContactEmail(dto.getContactEmail());
        department.setDescription(dto.getDescription());
        return department;
    }

    public DepartmentResponseDto toResponse(Department department) {
        if (department == null) return null;

        return new DepartmentResponseDto(
                department.getId(),
                department.getDepartmentName(),
                department.getContactEmail(),
                department.getDescription()
        );
    }
}
