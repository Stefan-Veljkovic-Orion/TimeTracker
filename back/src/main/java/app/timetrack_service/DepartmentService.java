package app.timetrack_service;

import app.timetracker_dto_implementation.response.DepartmentResponseDto;
import app.timetracker_entity_implemantion.Department;
import app.timetracker_mapper.DepartmentMapper;
import app.timetrack_repository.IDepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final IDepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(IDepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentResponseDto> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(departmentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
