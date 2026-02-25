package app.timeTracker_controller;

import app.timetrack_service.EmployeeService;
import app.timetracker_dto_implementation.EmpolyeeDto;
import app.timetracker_entity_implemantion.Employee;
import app.timetracker_mapper.EmployeeMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping
    public EmpolyeeDto createEmployee(@Valid @RequestBody EmpolyeeDto dto) {
        Employee employee = employeeMapper.toEntity(dto);
        Employee saved = employeeService.saveEmployee(employee, dto.getDepartmentID());
        return employeeMapper.toDto(saved);
    }
}