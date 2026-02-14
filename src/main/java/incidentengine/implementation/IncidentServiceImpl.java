package incidentengine.implementation;

import incidentengine.dto.IncidentCreateRequestDto;
import incidentengine.entity.Incident;
import incidentengine.enums.IncidentEnums;
import incidentengine.exception.ResourceNotFoundException;
import incidentengine.repository.IncidentRepository;
import incidentengine.service.IncidentService;
import incidentengine.validator.EnumValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;

    @Override
    public Incident createIncident(IncidentCreateRequestDto req) {

        IncidentEnums.Service service =
                EnumValidator.validateService(req.getService());

        IncidentEnums.Severity severity =
                EnumValidator.validateSeverity(req.getSeverity());

        Incident incident = Incident.builder()
                .title(req.getTitle())
                .summary(req.getSummary())
                .severity(String.valueOf(severity))
                .service(String.valueOf(service))
                .status(String.valueOf(IncidentEnums.Status.OPEN))
                .createdAt(new Date())
                .owner(req.getOwner())
                .build();

        return incidentRepository.save(incident);
    }

    @Override
    public Page<Incident> searchIncidents(
            String service,
            String severity,
            String status,
            Pageable pageable) {

        return incidentRepository.searchIncidents(
                service, severity, status, pageable);
    }

    @Override
    public Incident getIncidentById(Long id) {

        return incidentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Incident not found with id: " + id));
    }



}
