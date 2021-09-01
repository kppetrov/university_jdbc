package ua.com.foxminded.university.model;

import java.util.List;
import java.util.Objects;

public class Course {
    private int id;
    private String name;
    private List<Lesson> lessons;
    private List<Group> groups;

    public Course() {
    }

    public Course(int id, String name, List<Lesson> lessons, List<Group> groups) {
        this.id = id;
        this.name = name;
        this.lessons = lessons;
        this.groups = groups;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        return id == other.id && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + "]";
    }    
}
