package ua.com.foxminded.university.model;

import java.time.LocalDate;

public class Teacher extends AbstractPerson {

    public Teacher() {
        super();
    }

    public Teacher(int id, String firstName, String lastName, Gender gender, LocalDate birthdate) {
        super(id, firstName, lastName, gender, birthdate);
    }
}
