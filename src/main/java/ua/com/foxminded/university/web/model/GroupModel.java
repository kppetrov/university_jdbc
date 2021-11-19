package ua.com.foxminded.university.web.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import ua.com.foxminded.university.model.Group;

public class GroupModel {
    private int id;
    @NotBlank(message="{validation.name.NotBlank.message}")
    @Size(min=3, max=25, message="{validation.name.Size.message}")
    private String name;
    
    public GroupModel() {
        
    }
    
    public GroupModel(Group group) {
        this.id = group.getId();
        this.name = group.getName();
    }
    
    public Group toEntity() {
        return new Group(id, name);
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
}
