package app.timetrack_service;

import app.timetrack_repository.IEmployeeRepository;
import app.timetrack_repository.IDepartmentRepository;
import app.timetracker_entity_implemantion.Department;
import app.timetracker_entity_implemantion.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final IDepartmentRepository departmentRepository;

    public EmployeeService(IEmployeeRepository employeeRepository,
                           IDepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee saveEmployee(Employee employee, Integer departmentId) {
        Department dep = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found: " + departmentId));

        employee.setDepartment(dep);
        return employeeRepository.save(employee);
    }
}