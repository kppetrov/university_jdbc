package ua.com.foxminded.university.web.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import ua.com.foxminded.university.model.Gender;
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.model.Student;

public class StudentModel {
    private int id;
    private String firstName;
    private String lastName;
    private Gender gender;
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate birthdate;
    private int groupId;
    private String groupName;

    public StudentModel() {
        
    }
    
    public StudentModel(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.gender = student.getGender();
        this.birthdate = student.getBirthdate();
        if (student.getGroup() != null) {
            this.groupId = student.getGroup().getId();
            this.groupName = student.getGroup().getName();            
        }
    }

    public Student toEntity() {
        return new Student(id, firstName, lastName, gender, birthdate, new Group(groupId, groupName));
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
}
