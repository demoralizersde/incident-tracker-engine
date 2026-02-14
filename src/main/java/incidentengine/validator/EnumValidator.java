package incidentengine.validator;

import incidentengine.enums.IncidentEnums;
import incidentengine.exception.InvalidRequestException;

public class EnumValidator {

    public static IncidentEnums.Severity validateSeverity(String value) {
        try {
            return IncidentEnums.Severity.valueOf(value.toUpperCase());
        } catch (Exception ex) {
            throw new InvalidRequestException("Severity value is invalid");
        }
    }

    public static IncidentEnums.Service validateService(String value) {
        try {
            return IncidentEnums.Service.valueOf(value.toUpperCase());
        } catch (Exception ex) {
            throw new InvalidRequestException("Service name is invalid");
        }
    }

    public static IncidentEnums.Status validateStatus(String value) {
        try {
            return IncidentEnums.Status.valueOf(value.toUpperCase());
        } catch (Exception ex) {
            throw new InvalidRequestException("Status value is invalid");
        }
    }
}
