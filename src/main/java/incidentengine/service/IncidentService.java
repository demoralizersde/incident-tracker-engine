package incidentengine.service;

import incidentengine.dto.IncidentCreateRequestDto;
import incidentengine.entity.Incident;
import org.springframework.stereotype.Service;

@Service
public interface IncidentService {
    Incident createIncident(IncidentCreateRequestDto request);

}
