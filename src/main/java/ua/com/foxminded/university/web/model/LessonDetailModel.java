package ua.com.foxminded.university.web.model;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class LessonDetailModel {
    private int id;
    private String courseName; 
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate date;
    private String periodName;
    private String classroomName;
    private String teacherFirstName;
    private String teacherLastName;
    
    public LessonDetailModel() {
        
    }

    public LessonDetailModel(int id, String courseName, LocalDate date, String periodName, String classroomName,
            String teacherFirstName, String teacherLastName) {
        this.id = id;
        this.courseName = courseName;
        this.date = date;
        this.periodName = periodName;
        this.classroomName = classroomName;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
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

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroomName, courseName, date, id, periodName, teacherFirstName, teacherLastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LessonDetailModel other = (LessonDetailModel) obj;
        return Objects.equals(classroomName, other.classroomName) && Objects.equals(courseName, other.courseName)
                && Objects.equals(date, other.date) && id == other.id && Objects.equals(periodName, other.periodName)
                && Objects.equals(teacherFirstName, other.teacherFirstName)
                && Objects.equals(teacherLastName, other.teacherLastName);
    }
}
