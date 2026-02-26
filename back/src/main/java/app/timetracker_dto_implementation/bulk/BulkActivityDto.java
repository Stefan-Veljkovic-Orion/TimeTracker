package app.timetracker_dto_implementation.bulk;

import java.util.List;

public class BulkActivityDto {
    private List<Integer> savedIds;
    private List<FailedActivityDto> failedRecords;

        public BulkActivityDto(List<Integer> savedIds, List<FailedActivityDto> failedRecords) {
        this.savedIds = savedIds;
        this.failedRecords = failedRecords;
    }

    public List<Integer> getSavedIds() {
        return savedIds;
    }

    public void setSavedIds(List<Integer> savedIds) {
        this.savedIds = savedIds;
    }

    public List<FailedActivityDto> getFailedRecords() {
        return failedRecords;
    }

    public void setFailedRecords(List<FailedActivityDto> failedRecords) {
        this.failedRecords = failedRecords;
    }

    public BulkActivityDto() {
    }
}
