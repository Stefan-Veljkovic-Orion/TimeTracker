package app.timetrack_service;

import app.timetrack_repository.IEmployeeRepository;
import app.timetrack_repository.IDepartmentRepository;
import app.timetracker_dto_implementation.ActivityDto;
import app.timetracker_dto_implementation.bulk.BulkActivityDto;
import app.timetracker_dto_implementation.bulk.BulkEmployeesDto;
import app.timetracker_dto_implementation.bulk.FailedActivityDto;
import app.timetracker_dto_implementation.bulk.FailedEmployeeDto;
import app.timetracker_dto_implementation.csv.EmployeeCSVDto;
import app.timetracker_dto_implementation.EmployeeDto;
import app.timetracker_entity_implemantion.Activity;
import app.timetracker_entity_implemantion.Department;
import app.timetracker_entity_implemantion.Employee;
import app.timetracker_entity_implemantion.Project;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final IDepartmentRepository departmentRepository;

    public EmployeeService(IEmployeeRepository employeeRepository,
                           IDepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee saveEmployee(Employee employee, EmployeeDto dto) {

        Integer depId = dto.getDepartment().getId();

        Department dep = departmentRepository.findById(depId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Department not found: " + depId));

        employee.setDepartment(dep);
        return employeeRepository.save(employee);
    }


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeId(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employee not found: " + id));
    }

    public Employee updateEmployee(int id, EmployeeDto dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employee not found: " + id));

        Integer depId = dto.getDepartment().getId();
        Department dep = departmentRepository.findById(depId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Department not found: " + depId));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDateOfEmployment(dto.getDateOfEmployment());
        employee.setDepartment(dep);

        return employeeRepository.save(employee);

    }
    public List<EmployeeCSVDto> getAllEmployee(){
        List<Employee> lista = employeeRepository.findAll();
        return  lista.stream().map(e -> new EmployeeCSVDto(e.getId(),e.getName(),e.getEmail(),e.getDateOfEmployment(),e.getDepartment().getDepartmentName())).toList();
    }
    public BulkEmployeesDto bulkInsert(List<EmployeeDto> requests) {

        List<Integer> savedIds = new ArrayList<>();
        List<FailedEmployeeDto> failedRecords = new ArrayList<>();

        for (EmployeeDto dto : requests) {

            String validationError = validate(dto);
            if (validationError != null) {
                failedRecords.add(new FailedEmployeeDto(dto, validationError));
                continue;
            }

            try {

                Employee employee = new Employee();
                employee.setName(dto.getName());
                employee.setDepartment(dto.getDepartment());
                employee.setEmail(dto.getEmail());
                employee.setDateOfEmployment(dto.getDateOfEmployment());
                employee.setActivities(dto.getActivities());

                Employee saved = employeeRepository.save(employee);
                savedIds.add(saved.getId());

            } catch (Exception e) {
                failedRecords.add(new FailedEmployeeDto(dto, "Database error: " + e.getMessage()));
            }
        }

        return new BulkEmployeesDto(failedRecords, savedIds);
    }
    private String validate(EmployeeDto dto) {

        if (dto.getName().split(" ").length < 2) return "Employee must have first and last name";
        if (!dto.getEmail().contains("orion")) return "Email must be orion";
        return null;
    }
}