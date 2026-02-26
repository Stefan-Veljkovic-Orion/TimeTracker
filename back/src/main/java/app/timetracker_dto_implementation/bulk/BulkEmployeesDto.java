package app.timetracker_dto_implementation.bulk;

import java.util.ArrayList;
import java.util.List;

public class BulkEmployeesDto {
    private List<FailedEmployeeDto> failedRecords = new ArrayList<>();
    private List<Integer> successIds = new ArrayList<>();

    public List<FailedEmployeeDto> getFailedRecords() {
        return failedRecords;
    }

    public void setFailedRecords(List<FailedEmployeeDto> failedRecords) {
        this.failedRecords = failedRecords;
    }

    public List<Integer> getSuccessIds() {
        return successIds;
    }

    public void setSuccessIds(List<Integer> successIds) {
        this.successIds = successIds;
    }

    public BulkEmployeesDto(List<FailedEmployeeDto> failed, List<Integer> success) {
        this.failedRecords = failed;
        this.successIds = success;
    }
}
