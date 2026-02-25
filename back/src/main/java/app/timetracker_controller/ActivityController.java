package app.timetracker_controller;

import app.timetracker_dto_implementation.ActivityDto;
import app.timetracker_dto_implementation.ActivityResponseDto;
import app.timetracker_dto_implementation.BulkActivityDto;
import app.timetracker_entity_implemantion.Activity;
import app.timetracker_mapper.ActivityMapper;
import app.timetrack_service.ActivityService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {


    private final ActivityService activityService;
    private final ActivityMapper activityMapper;

    public ActivityController(ActivityService activityService,  ActivityMapper activityMapper) {
        this.activityService = activityService;
        this.activityMapper = activityMapper;
    }

    @PostMapping(value = "/bulk-insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BulkActivityDto> bulkInsert(@RequestBody List<ActivityDto> activities) {
        BulkActivityDto response = activityService.bulkInsert(activities);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ActivityResponseDto> create(
            @Valid @RequestBody ActivityDto dto,
            UriComponentsBuilder uriBuilder) {

        Activity saved = activityService.create(dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/activities/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri())
                .body(ActivityResponseDto.from(saved));
    }

}

