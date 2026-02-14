package incidentengine.controller;


import incidentengine.dto.ApiResponse;
import incidentengine.dto.IncidentCreateRequestDto;
import incidentengine.dto.PageResponseDto;
import incidentengine.entity.Incident;
import incidentengine.service.IncidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Incident>> createIncident(
            @Valid @RequestBody IncidentCreateRequestDto request) {

        Incident incident = incidentService.createIncident(request);

        ApiResponse<Incident> response =
                new ApiResponse<>(true, 200,
                        "Incident created successfully", incident);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDto<Incident>>> getIncidents(
            @RequestParam(required = false) String service,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String status,
            Pageable pageable) {

        Page<Incident> incidents =
                incidentService.searchIncidents(service, severity, status, pageable);

        PageResponseDto<Incident> pageResponse = new PageResponseDto<>(incidents);

        return ResponseEntity.ok(
                new ApiResponse<>(true, 200,
                        "Incidents fetched successfully", pageResponse)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Incident>> getIncidentById(
            @PathVariable Long id) {

        Incident incident = incidentService.getIncidentById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, 200,
                        "Incident fetched successfully", incident)
        );
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Incident>> updateStatus(
            @PathVariable Long id,
            @RequestParam(required = true) String status) {

        Incident incident = incidentService.updateStatus(id, status);

        return ResponseEntity.ok(
                new ApiResponse<>(true, 200,
                        "Incident status updated successfully", incident)
        );
    }



}
