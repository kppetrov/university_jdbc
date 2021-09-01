package ua.com.foxminded.university.model;

import java.time.LocalDate;
import java.util.Objects;

public abstract class AbstractPerson {
    private int id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthdate;

    protected AbstractPerson() {
    }

    protected AbstractPerson(int id, String firstName, String lastName, Gender gender, LocalDate birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthdate, firstName, gender, id, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractPerson other = (AbstractPerson) obj;
        return Objects.equals(birthdate, other.birthdate) && Objects.equals(firstName, other.firstName)
                && gender == other.gender && id == other.id && Objects.equals(lastName, other.lastName);
    }  
}
