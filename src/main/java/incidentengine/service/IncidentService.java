package incidentengine.service;

import incidentengine.dto.IncidentCreateRequestDto;
import incidentengine.entity.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IncidentService {
    Incident createIncident(IncidentCreateRequestDto request);

    Page<Incident> searchIncidents(
            String service,
            String severity,
            String status,
            Pageable pageable);

    Incident getIncidentById(Long id);


}
