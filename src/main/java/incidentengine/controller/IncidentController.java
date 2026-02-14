package incidentengine.controller;


import incidentengine.dto.ApiResponse;
import incidentengine.dto.IncidentCreateRequestDto;
import incidentengine.entity.Incident;
import incidentengine.service.IncidentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
}
