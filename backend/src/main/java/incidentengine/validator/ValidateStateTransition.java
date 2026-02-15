package incidentengine.validator;

import incidentengine.exception.InvalidRequestException;
import org.springframework.stereotype.Component;
import static incidentengine.enums.IncidentEnums.Status.*;

@Component
public class ValidateStateTransition {

    public void validate(String current, String next) {

        if (current.equals(RESOLVED.name())) {
            throw new InvalidRequestException("Resolved incident cannot be updated");
        }

        if (current.equals(OPEN.name())) {
            if (!next.equals(MITIGATED.name()) && !next.equals(RESOLVED.name())) {
                throw new InvalidRequestException(
                        "OPEN incidents can only move to MITIGATED or RESOLVED");
            }
        }

        if (current.equals("MITIGATED")) {
            if (!next.equals("RESOLVED")) {
                throw new InvalidRequestException(
                        "MITIGATED incidents can only move to RESOLVED");
            }
        }
    }

}
