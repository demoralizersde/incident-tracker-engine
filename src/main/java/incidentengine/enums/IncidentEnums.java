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
        PAYMENT_SERVICE,
        ORDER_SERVICE,
        AUTH_SERVICE,
        NOTIFICATION_SERVICE,
        INVENTORY_SERVICE
    }
}
