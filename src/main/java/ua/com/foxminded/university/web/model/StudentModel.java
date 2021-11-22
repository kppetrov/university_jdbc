package ua.com.foxminded.university.web.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import ua.com.foxminded.university.model.Gender;

public class StudentModel {
    private int id;
    @NotBlank(message = "{validation.person.firstName.NotBlank.message}")
    private String firstName;
    @NotBlank(message = "{validation.person.lastName.NotBlank.message}")
    private String lastName;
    private Gender gender;
    @DateTimeFormat(iso = ISO.DATE)
    @NotNull(message = "{validation.person.birthdate.NotNull.message}")
    private LocalDate birthdate;
    private int groupId;
    private String groupName;

    public StudentModel() {

    }

    public StudentModel(int id, String firstName, String lastName, Gender gender, LocalDate birthdate, int groupId,
            String groupName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthdate = birthdate;
        this.groupId = groupId;
        this.groupName = groupName;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthdate, firstName, gender, groupId, groupName, id, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentModel other = (StudentModel) obj;
        return Objects.equals(birthdate, other.birthdate) && Objects.equals(firstName, other.firstName)
                && gender == other.gender && groupId == other.groupId && Objects.equals(groupName, other.groupName)
                && id == other.id && Objects.equals(lastName, other.lastName);
    }    
}
