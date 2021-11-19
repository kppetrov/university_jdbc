package ua.com.foxminded.university.web.model;

import ua.com.foxminded.university.model.Student;

public class StudentListModel {
    private int id;
    private String firstName;
    private String lastName;
    private String groupName;

    public StudentListModel() {
        
    }
    
    public StudentListModel(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        if (student.getGroup() != null) {
            this.groupName = student.getGroup().getName();            
        }
    }

    public Student toEntity() {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        return student;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
