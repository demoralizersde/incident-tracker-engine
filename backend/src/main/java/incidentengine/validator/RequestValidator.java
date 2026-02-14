package incidentengine.validator;

import incidentengine.enums.IncidentEnums;
import incidentengine.exception.InvalidRequestException;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

    public IncidentEnums.Severity validateSeverity(String value) {
        try {
            return IncidentEnums.Severity.valueOf(value.toUpperCase());
        } catch (Exception ex) {
            throw new InvalidRequestException("Severity value is invalid");
        }
    }

    public IncidentEnums.Service validateService(String value) {
        try {
            return IncidentEnums.Service.valueOf(value.toUpperCase());
        } catch (Exception ex) {
            throw new InvalidRequestException("Service name is invalid");
        }
    }

    public IncidentEnums.Status validateStatus(String value) {
        try {
            return IncidentEnums.Status.valueOf(value.toUpperCase());
        } catch (Exception ex) {
            throw new InvalidRequestException("Status value is invalid");
        }
    }
}
