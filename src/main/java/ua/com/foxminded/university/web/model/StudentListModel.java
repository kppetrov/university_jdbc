package ua.com.foxminded.university.web.model;

import java.util.Objects;

public class StudentListModel {
    private int id;
    private String firstName;
    private String lastName;
    private String groupName;

    public StudentListModel() {
        
    }    

    public StudentListModel(int id, String firstName, String lastName, String groupName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, groupName, id, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentListModel other = (StudentListModel) obj;
        return Objects.equals(firstName, other.firstName) && Objects.equals(groupName, other.groupName)
                && id == other.id && Objects.equals(lastName, other.lastName);
    }
}
