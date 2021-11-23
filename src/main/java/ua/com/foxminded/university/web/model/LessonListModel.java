package ua.com.foxminded.university.web.model;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class LessonListModel {
    private int id;
    private String courseName;
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate date;
    private String periodName;
    
    public LessonListModel() {
        
    }

    public LessonListModel(int id, String courseName, LocalDate date, String periodName) {
        this.id = id;
        this.courseName = courseName;
        this.date = date;
        this.periodName = periodName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, date, id, periodName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LessonListModel other = (LessonListModel) obj;
        return Objects.equals(courseName, other.courseName) && Objects.equals(date, other.date) && id == other.id
                && Objects.equals(periodName, other.periodName);
    }
}
