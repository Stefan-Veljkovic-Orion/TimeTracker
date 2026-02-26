package app.timetracker_mapper;

import app.timetracker_dto_implementation.EmployeeDto;
import app.timetracker_entity_implemantion.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EmployeeMapper implements DtoEntityMapper<EmployeeDto, Employee> {

    @Override
    public EmployeeDto toDto(Employee employee) {
        if (employee == null) return null;

        Integer departmentId = (employee.getDepartment() != null)
                ? employee.getDepartment().getId()
                : null;

        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDateOfEmployment(employee.getDateOfEmployment());
        dto.setDepartment(employee.getDepartment());



        dto.setActivities(new ArrayList<>());

        return dto;
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        if (dto == null) return null;

        Employee employee = new Employee();


        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDateOfEmployment(dto.getDateOfEmployment());
        employee.setDepartment(dto.getDepartment());

        employee.setActivities(null);
        return employee;
    }
}