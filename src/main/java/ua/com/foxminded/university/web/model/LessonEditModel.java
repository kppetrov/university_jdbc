package ua.com.foxminded.university.web.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class LessonEditModel {
    private int id;
    private int courseId; 
    @DateTimeFormat(iso = ISO.DATE)
    @NotNull(message = "{validation.lesson.date.NotNull.message}")
    private LocalDate date;
    private int periodId;
    private int classroomId;
    private int teacherId;    
    
    public LessonEditModel() {
        
    }

    public LessonEditModel(int id, int courseId, LocalDate date, int periodId, int classroomId, int teacherId) {
        this.id = id;
        this.courseId = courseId;
        this.date = date;
        this.periodId = periodId;
        this.classroomId = classroomId;
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroomId, courseId, date, id, periodId, teacherId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LessonEditModel other = (LessonEditModel) obj;
        return classroomId == other.classroomId && Objects.equals(courseId, other.courseId)
                && Objects.equals(date, other.date) && id == other.id && periodId == other.periodId
                && teacherId == other.teacherId;
    }
}
