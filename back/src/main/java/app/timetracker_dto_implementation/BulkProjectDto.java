package app.timetracker_dto_implementation;

import java.util.List;

public class BulkProjectDto {
    private List<Integer> savedIds;
    private List<FailedProjectDto> failedRecords;

    public BulkProjectDto(List<Integer> savedIds, List<FailedProjectDto> failedRecords) {
        this.savedIds = savedIds;
        this.failedRecords = failedRecords;
    }

    public List<Integer> getSavedIds() {
        return savedIds;
    }

    public void setSavedIds(List<Integer> savedIds) {
        this.savedIds = savedIds;
    }

    public List<FailedProjectDto> getFailedRecords() {
        return failedRecords;
    }

    public void setFailedRecords(List<FailedProjectDto> failedRecords) {
        this.failedRecords = failedRecords;
    }
    
    
}
