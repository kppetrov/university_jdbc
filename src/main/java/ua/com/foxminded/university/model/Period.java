package ua.com.foxminded.university.model;

import java.time.LocalTime;
import java.util.Objects;

public class Period {
    private int id;
    private String name;
    private LocalTime start;
    private LocalTime end;

    public Period() {
    }

    public Period(int id, String name, LocalTime start, LocalTime end) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
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

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(end, id, name, start);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Period other = (Period) obj;
        return Objects.equals(end, other.end) && id == other.id && Objects.equals(name, other.name)
                && Objects.equals(start, other.start);
    }

    @Override
    public String toString() {
        return "Period [id=" + id + ", name=" + name + ", start=" + start + ", end=" + end + "]";
    }    
}
