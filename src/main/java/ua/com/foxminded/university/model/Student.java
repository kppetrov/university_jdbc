package ua.com.foxminded.university.model;

import java.time.LocalDate;

public class Student extends AbstractPerson {

    public Student() {
        super();
    }

    public Student(int id, String firstName, String lastName, Gender gender, LocalDate birthdate) {
        super(id, firstName, lastName, gender, birthdate);
    }
}
