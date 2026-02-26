package app.timetracker_controller;

import app.timetrack_service.EmployeeService;
import app.timetracker_dto_implementation.bulk.BulkEmployeesDto;
import app.timetracker_dto_implementation.csv.EmployeeCSVDto;
import app.timetracker_dto_implementation.EmployeeDto;
import app.timetracker_entity_implemantion.Employee;
import app.timetracker_mapper.EmployeeMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;

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
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto dto) {
        Employee employee = employeeMapper.toEntity(dto);
        Employee saved = employeeService.saveEmployee(employee,dto);
        return employeeMapper.toDto(saved);
    }

    @GetMapping(value = "/export-csv", produces = "text/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=employee.csv");
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] header = {"ID", "Date of employment", "Department", "Email", "Name"};
        String[] fieldMapping = {"id", "dateOfEmployment", "departmentName", "email", "name"};
        csvWriter.writeHeader(header);
        for (EmployeeCSVDto e : employeeService.getAllEmployee()) {
            csvWriter.write(e, fieldMapping);
        }


        csvWriter.close();
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees()
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeId(@PathVariable int id) {
        return employeeMapper.toDto(employeeService.getEmployeeId(id));
    }
    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Integer id, @Valid @RequestBody EmployeeDto dto) {
        Employee updated = employeeService.updateEmployee(id, dto);
        return employeeMapper.toDto(updated);
    }

    @PostMapping("/bulk-insert")
    public ResponseEntity<BulkEmployeesDto> bulkInsert(@RequestBody List<EmployeeDto> employees) {
        BulkEmployeesDto response = employeeService.bulkInsert(employees);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);

    }
}