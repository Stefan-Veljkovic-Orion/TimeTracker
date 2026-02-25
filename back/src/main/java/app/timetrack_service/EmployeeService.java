package app.timetrack_service;

import app.timetrack_repository.IActivityRepository;
import app.timetrack_repository.IEmployeeRepository;
import app.timetrack_repository.IDepartmentRepository;
import app.timetracker_dto_implementation.EmpolyeeDto;
import app.timetracker_entity_implemantion.Department;
import app.timetracker_entity_implemantion.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final IDepartmentRepository departmentRepository;


    private final IActivityRepository activityRepository;

    public EmployeeService(IEmployeeRepository employeeRepository,
                           IDepartmentRepository departmentRepository,IActivityRepository activityRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.activityRepository = activityRepository;

    }

    public Employee saveEmployee(Employee employee, EmpolyeeDto dto) {

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

    public Employee updateEmployee(int id, EmpolyeeDto dto) {
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
    public void deleteEmployee(int id) {
        Employee e = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        if (activityRepository.existsByEmployee_Id(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot delete employee with activities");
        }

        employeeRepository.delete(e);
    }
}