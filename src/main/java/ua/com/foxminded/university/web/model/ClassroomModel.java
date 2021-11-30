package ua.com.foxminded.university.web.model;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClassroomModel {
    private int id;
    @NotBlank(message = "{validation.name.NotBlank.message}")
    @Size(min = 3, max = 25, message = "{validation.name.Size.message}")
    private String name;

    public ClassroomModel() {

    }

    public ClassroomModel(int id, String name) {
        this.id = id;
        this.name = name;
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
        ClassroomModel other = (ClassroomModel) obj;
        return id == other.id && Objects.equals(name, other.name);
    }
}
