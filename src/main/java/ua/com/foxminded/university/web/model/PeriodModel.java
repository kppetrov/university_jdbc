package ua.com.foxminded.university.web.model;

import java.time.LocalTime;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class PeriodModel {
    private int id;
    @NotBlank(message="{validation.name.NotBlank.message}")
    @Size(min=3, max=25, message="{validation.name.Size.message}")
    private String name;
    @DateTimeFormat(iso = ISO.TIME)
    @NotNull(message = "{validation.period.start.NotNull.message}")
    private LocalTime start;
    @DateTimeFormat(iso = ISO.TIME)
    @NotNull(message = "{validation.period.end.NotNull.message}")
    private LocalTime end;
    
    public PeriodModel() {

    }
    
    public PeriodModel(int id, String name, LocalTime start, LocalTime end) {
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
        PeriodModel other = (PeriodModel) obj;
        return Objects.equals(end, other.end) && id == other.id && Objects.equals(name, other.name)
                && Objects.equals(start, other.start);
    }    
}
