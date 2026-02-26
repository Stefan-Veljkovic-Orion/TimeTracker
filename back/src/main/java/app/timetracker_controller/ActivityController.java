package app.timetracker_controller;

import app.timetracker_dto_implementation.bulk.BulkActivityDto;
import app.timetracker_dto_implementation.csv.ActivityCSVDto;
import app.timetracker_dto_implementation.ActivityDto;
import app.timetrack_service.ActivityService;
import app.timetracker_dto_implementation.response.ActivityResponseDto;
import app.timetracker_entity_implemantion.Activity;
import app.timetracker_mapper.ActivityMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
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

    @GetMapping(value = "/export-csv", produces = "text/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {

            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=activity.csv");
            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            String[] header = {"ID","Employee Name", "Project Name", "Description", "Time of activity"};
            String[] fieldMapping = {"id", "employeeName", "projectName","description","timeOfActivity"};
            csvWriter.writeHeader(header);
            for(ActivityCSVDto a: activityService.getAllActivity()){
                csvWriter.write(a,fieldMapping);
            }


            csvWriter.close();
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActivityResponseDto>> getAllActivities() {
        List<ActivityResponseDto> activities = activityService.getActivities();
        return ResponseEntity.ok(activities);
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

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ActivityResponseDto>> getEmployeeActivities(
            @PathVariable Integer employeeId,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate) {

        List<Activity> activities = activityService.findActivitiesForEmployee(employeeId, fromDate, toDate);

        List<ActivityResponseDto> response = activities.stream()
                .map(activityMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponseDto> update(
            @PathVariable Integer id,
            @Valid @RequestBody ActivityDto dto) {
        Activity updated = activityService.update(id, dto);
        return ResponseEntity.ok(ActivityResponseDto.from(updated));
    }


}

