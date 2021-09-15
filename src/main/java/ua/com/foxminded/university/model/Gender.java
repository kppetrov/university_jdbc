package ua.com.foxminded.university.model;

public enum Gender {
    MAIL("MAIL"),
    FEMAIL("FEMAIL");
    
    private final String value;    
    
    Gender(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static Gender of(String value) {
        for (Gender e : values()) {
            if (value.equals(e.value)) {
                return e;
            }
        }
        return null;
    }
}
