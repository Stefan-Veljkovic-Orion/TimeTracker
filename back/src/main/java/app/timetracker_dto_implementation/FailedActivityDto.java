package app.timetracker_dto_implementation;

public class FailedActivityDto {
    private ActivityDto activity;
    private String reason;

    public FailedActivityDto(ActivityDto activity, String reason) {
        this.activity = activity;
        this.reason = reason;
    }

    public FailedActivityDto() {
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public String getReason() {
        return reason;
    }
}
