package ua.com.foxminded.university.model;

import java.time.LocalDate;
import java.util.Objects;

public class Lesson {
    private int id;
    private LocalDate date;
    private Period period;
    private Classroom classroom;
    private Teacher teacher;

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

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
    
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroom, date, id, period);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lesson other = (Lesson) obj;
        return Objects.equals(classroom, other.classroom) && Objects.equals(date, other.date) && id == other.id
                && Objects.equals(period, other.period);
    }

    @Override
    public String toString() {
        return "Lesson [id=" + id + ", date=" + date + ", period=" + period + ", classroom=" + classroom + ", teacher="
                + teacher + "]";
    }    
}
