package incidentengine.enums;

public class IncidentEnums {

    public enum Severity {
        SEV1,
        SEV2,
        SEV3,
        SEV4
    }

    public enum Status {
        OPEN,
        MITIGATED,
        RESOLVED
    }

    public enum Service {
        PAYMENTS_SERVICE,
        ORDER_SERVICE,
        DATABASE,
        FRONTEND,
        BACKEND,
        API_GATEWAY
    }
}
