package ua.com.foxminded.university.dao.jdbc;

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
    private static final String GET_ALL = 
            "select "
                + "l.id as lesson_id, "
                + "l.date as lesson_date, "
                + "c.id as course_id, "
                + "c.name as course_name, "
                + "p.id as period_id, "
                + "p.name as period_name, "
                + "p.start_time, "
                + "p.end_time, "
                + "cr.id as classroom_id, "
                + "cr.name as classroom_name, "
                + "t.id as teacher_id, "
                + "t.first_name, "
                + "t.last_name, "
                + "t.gender, "
                + "t.birthdate "
            + "from lessons l "
                + "left join courses as c "
                    + "on l.course_id = c.id "
                + "left join periods as p "
                    + "on l.period_id = p.id "
                + "left join classrooms as cr "
                    + "on l.classroom_id = cr.id "
                + "left join teachers as t "
                    + "on l.teacher_id = t.id";
    private static final String GET_BY_ID = GET_ALL + " where l.id = :id";
    private static final String GET_BY_COURSE_ID = GET_ALL + " where l.course_id = :course_id";  
    private static final String GET_BY_TEACHER_ID = GET_ALL + " where l.teacher_id = :teacher_id";
    private static final String GET_BY_GROUP_ID = 
            GET_ALL
            + " left join course_group as cg "
                    + "on l.course_id = cg.course_id "
            + " where cg.group_id = :group_id";
    private static final String INSERT = 
            "insert into lessons (date, course_id, period_id, classroom_id, teacher_id) "
          + "values (:date, :course_id, :period_id, :classroom_id, :teacher_id)";
    private static final String UPDATE = 
            "update lessons "
               + "set "
                   + "date = :date, "
                   + "course_id = :course_id, "
                   + "period_id = :period_id, "
                   + "classroom_id = :classroom_id, "
                   + "teacher_id = :teacher_id "
               + "where id = :id";
    private static final String DELETE = "delete from lessons where id = :id";

    private LessonMapper lessonMapper;
    
    @Autowired
    public void setLessonWithDetailExtractor(LessonMapper lessonMapper) {
        this.lessonMapper = lessonMapper;
    }

    @Override
    public List<Lesson> getAll() {
        return jdbcTemplate.query(GET_ALL, lessonMapper);
    }

    @Override
    public Lesson getById(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        List<Lesson> lessons = jdbcTemplate.query(GET_BY_ID, namedParameters, lessonMapper);
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
        jdbcTemplate.update(INSERT, namedParameters, keyHolder, new String[] { "id" });
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
        return jdbcTemplate.update(UPDATE, namedParameters);
    }

    @Override
    public int delete(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return jdbcTemplate.update(DELETE, namedParameters);
    }
    
    @Override
    public List<Lesson> getByGroupId(int groupId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("group_id", groupId);
        return jdbcTemplate.query(GET_BY_GROUP_ID, namedParameters, lessonMapper);
    }

    @Override
    public List<Lesson> getByTeacherId(int teacherId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("teacher_id", teacherId);
        return jdbcTemplate.query(GET_BY_TEACHER_ID, namedParameters, lessonMapper);
    }

    @Override
    public List<Lesson> getByCourseId(int curseId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("course_id", curseId);
        return jdbcTemplate.query(GET_BY_COURSE_ID, namedParameters, lessonMapper);
    }
}
