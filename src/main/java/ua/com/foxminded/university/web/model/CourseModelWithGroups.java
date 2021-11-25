package ua.com.foxminded.university.web.model;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CourseModelWithGroups {
    private int id;
    @NotBlank(message="{validation.name.NotBlank.message}")
    @Size(min=3, max=25, message="{validation.name.Size.message}")
    private String name;
    private List<GroupModel> groups;    
    
    public CourseModelWithGroups() {
        
    }
    
    public CourseModelWithGroups(int id, String name, List<GroupModel> groups) {
        this.id = id;
        this.name = name;
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

    public List<GroupModel> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupModel> groups) {
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
        CourseModelWithGroups other = (CourseModelWithGroups) obj;
        return id == other.id && Objects.equals(name, other.name);
    }    
}
