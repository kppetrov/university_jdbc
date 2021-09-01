package ua.com.foxminded.university.model;

public enum Gender {
    MAIL(1),
    FEMAIL(2);
    
    private final int id;    
    
    Gender(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public static Gender of(int id) {
        for (Gender e : values()) {
            if (id == e.id) {
                return e;
            }
        }
        return null;
    }
}
