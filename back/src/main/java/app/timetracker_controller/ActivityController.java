package app.timetracker_controller;

import app.timetracker_dto_implementation.ActivityDto;
import app.timetracker_dto_implementation.BulkActivityDto;
import app.timetracker_service.ActivityService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {


        private final ActivityService activityService;

        public ActivityController(ActivityService activityService) {
            this.activityService = activityService;
        }

    @PostMapping(value = "/bulk-insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BulkActivityDto> bulkInsert(@RequestBody List<ActivityDto> activities) {
        BulkActivityDto response = activityService.bulkInsert(activities);
        return ResponseEntity.ok(response);
    }
    }

