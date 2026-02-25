package app.timeTracker_controller;

import app.timetrack_service.EmployeeService;
import app.timetracker_dto_implementation.EmpolyeeDto;
import app.timetracker_entity_implemantion.Employee;
import app.timetracker_mapper.EmployeeMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Employee saved = employeeService.saveEmployee(employee,dto);
        return employeeMapper.toDto(saved);
    }
    @GetMapping
    public List<EmpolyeeDto> getAllEmployees() {
        return employeeService.getAllEmployees()
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }
    @GetMapping("/{id}")
    public EmpolyeeDto getEmployeeId(@PathVariable int id) {
        return employeeMapper.toDto(employeeService.getEmployeeId(id));
    }
    @PutMapping("/{id}")
    public EmpolyeeDto updateEmployee(@PathVariable Integer id, @Valid @RequestBody EmpolyeeDto dto) {
        Employee updated = employeeService.updateEmployee(id, dto);
        return employeeMapper.toDto(updated);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }
}