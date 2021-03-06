package ua.com.foxminded.university.model;

import java.time.LocalDate;

public class Student extends AbstractPerson {
    private Group group;

    public Student() {
        super();
    }

    public Student(int id, String firstName, String lastName, Gender gender, LocalDate birthdate, Group group) {
        super(id, firstName, lastName, gender, birthdate);
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!super.equals(obj)) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Student [id=" + getId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", gender="
                + getGender() + ", birthdate=" + getBirthdate() + ",group=" + group + "]";
    }
}
