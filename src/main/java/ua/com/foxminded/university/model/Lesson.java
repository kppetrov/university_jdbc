package ua.com.foxminded.university.model;

import java.time.LocalDate;

public class Lesson {
    private int id;
    private LocalDate date;
    private Period period;
    private Teacher teacher;
    private Classroom classroom;

    public Lesson() {
    }

    public Lesson(int id, LocalDate date, Period period, Teacher teacher, Classroom classroom) {
        this.id = id;
        this.date = date;
        this.period = period;
        this.teacher = teacher;
        this.classroom = classroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
