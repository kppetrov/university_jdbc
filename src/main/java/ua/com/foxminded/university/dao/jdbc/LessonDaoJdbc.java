package ua.com.foxminded.university.dao.jdbc;

import static ua.com.foxminded.university.dao.jdbc.Query.LESSON_GET_ALL;
import static ua.com.foxminded.university.dao.jdbc.Query.LESSON_GET_BY_ID;
import static ua.com.foxminded.university.dao.jdbc.Query.LESSON_GET_BY_COURSE_ID;  
import static ua.com.foxminded.university.dao.jdbc.Query.LESSON_GET_BY_TEACHER_ID;
import static ua.com.foxminded.university.dao.jdbc.Query.LESSON_GET_BY_GROUP_ID;
import static ua.com.foxminded.university.dao.jdbc.Query.LESSON_INSERT;
import static ua.com.foxminded.university.dao.jdbc.Query.LESSON_UPDATE;
import static ua.com.foxminded.university.dao.jdbc.Query.LESSON_DELETE;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dao.jdbc.mappers.LessonMapper;
import ua.com.foxminded.university.model.Lesson;

@Repository
public class LessonDaoJdbc extends AbstractDAO implements LessonDao {
    private LessonMapper lessonMapper;
    
    @Autowired
    public void setLessonWithDetailExtractor(LessonMapper lessonMapper) {
        this.lessonMapper = lessonMapper;
    }

    @Override
    public List<Lesson> getAll() {
        return jdbcTemplate.query(LESSON_GET_ALL, lessonMapper);
    }

    @Override
    public Lesson getById(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        List<Lesson> lessons = jdbcTemplate.query(LESSON_GET_BY_ID, namedParameters, lessonMapper);
        if (lessons.isEmpty()) {
            return new Lesson();
        }
        return lessons.get(0);
    } 

    @Override
    public Lesson insert(Lesson item) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("date", Date.valueOf(item.getDate()))
                .addValue("course_id", item.getCourse().getId())
                .addValue("period_id", item.getPeriod().getId())
                .addValue("classroom_id", item.getClassroom().getId())
                .addValue("teacher_id", item.getTeacher().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(LESSON_INSERT, namedParameters, keyHolder, new String[] { "id" });
        return new Lesson(keyHolder.getKeyAs(Integer.class), item.getCourse(), item.getDate(), item.getPeriod(),
                item.getTeacher(), item.getClassroom());
    }

    @Override
    public int update(Lesson item) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", item.getId())
                .addValue("date", Date.valueOf(item.getDate()))
                .addValue("course_id", item.getCourse().getId())
                .addValue("period_id", item.getPeriod().getId())
                .addValue("classroom_id", item.getClassroom().getId())
                .addValue("teacher_id", item.getTeacher().getId());
        return jdbcTemplate.update(LESSON_UPDATE, namedParameters);
    }

    @Override
    public int delete(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return jdbcTemplate.update(LESSON_DELETE, namedParameters);
    }
    
    @Override
    public List<Lesson> getByGroupId(int groupId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("group_id", groupId);
        return jdbcTemplate.query(LESSON_GET_BY_GROUP_ID, namedParameters, lessonMapper);
    }

    @Override
    public List<Lesson> getByTeacherId(int teacherId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("teacher_id", teacherId);
        return jdbcTemplate.query(LESSON_GET_BY_TEACHER_ID, namedParameters, lessonMapper);
    }

    @Override
    public List<Lesson> getByCourseId(int curseId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("course_id", curseId);
        return jdbcTemplate.query(LESSON_GET_BY_COURSE_ID, namedParameters, lessonMapper);
    }
}
