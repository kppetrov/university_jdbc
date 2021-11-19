package ua.com.foxminded.university.web.model;

import ua.com.foxminded.university.model.Group;

public class GroupModel {
    private int id;
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
