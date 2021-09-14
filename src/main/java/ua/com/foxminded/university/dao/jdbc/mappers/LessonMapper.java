package ua.com.foxminded.university.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.com.foxminded.university.model.Lesson;

@Component
public class LessonMapper implements RowMapper<Lesson>{
    private ClassroomMapper classroomMapper;
    private CourseMapper courseMapper;
    private PeriodMapper periodMapper;
    private TeacherMapper teacherMapper; 

    @Autowired
    public void setClassroomMapper(ClassroomMapper classroomMapper) {
        this.classroomMapper = classroomMapper;
    }
    
    @Autowired
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }
    
    @Autowired
    public void setPeriodMapper(PeriodMapper periodMapper) {
        this.periodMapper = periodMapper;
    }
    
    @Autowired
    public void setTeacherMapper(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public Lesson mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setId(resultSet.getInt("lesson_id"));
        lesson.setDate(resultSet.getDate("lesson_date").toLocalDate());
        lesson.setCourse(courseMapper.mapRow(resultSet, rowNum));
        lesson.setPeriod(periodMapper.mapRow(resultSet, rowNum));
        lesson.setTeacher(teacherMapper.mapRow(resultSet, rowNum));
        lesson.setClassroom(classroomMapper.mapRow(resultSet, rowNum));        
        return lesson;
    }
}
