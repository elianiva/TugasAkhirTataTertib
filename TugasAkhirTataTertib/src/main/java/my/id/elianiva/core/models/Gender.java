package my.id.elianiva.core.models;

public enum Gender {
    MALE,
    FEMALE;

    public String toString() {
        return switch (this) {
            case MALE -> "Male";
            case FEMALE -> "Female";
        };
    }

    public static Gender fromString(String gender) {
        return switch (gender) {
            case "M" -> MALE;
            case "F" -> FEMALE;
            default -> null;
        };
    }
}
