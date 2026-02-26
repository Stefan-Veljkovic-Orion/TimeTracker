package app.timetracker_dto_implementation.bulk;

import app.timetracker_dto_implementation.EmployeeDto;

public class FailedEmployeeDto {
    private EmployeeDto employee;
    private String reason;

    public FailedEmployeeDto(EmployeeDto employee, String reason) {
        this.employee = employee;
        this.reason = reason;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
