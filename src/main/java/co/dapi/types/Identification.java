package co.dapi.types;

public class Identification {
    private final String value;
    private final IdentificationType type;

    public Identification(String value, IdentificationType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public IdentificationType getType() {
        return type;
    }

    public enum IdentificationType {
        passport,
        national_id,
        visa_number
    }
}
