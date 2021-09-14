package ua.com.foxminded.university.dao.jdbc;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.dao.jdbc.mappers.TeacherMapper;
import ua.com.foxminded.university.model.Teacher;

@Repository
public class TeacherDaoJdbc extends AbstractDAO implements TeacherDao {
    private static final String GET_ALL = "select id as teacher_id, first_name, last_name, gender, birthdate from teachers";
    private static final String GET_BY_ID = "select id as teacher_id, first_name, last_name, gender, birthdate from teachers where id = :id";
    private static final String INSERT = "insert into teachers (first_name, last_name, gender, birthdate) "
                                       + "values (:first_name, :last_name, :gender, :birthdate)";
    private static final String UPDATE = "update teachers "
                                       + "set first_name = :first_name, last_name = :last_name, gender = :gender, birthdate = :birthdate "
                                       + "where id = :id";
    private static final String DELETE = "delete from teachers where id = :id";
    
    TeacherMapper teacherMapper;   
    
    @Autowired
    public void setTeacherMapper(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public List<Teacher> getAll() {
        return jdbcTemplate.query(GET_ALL, teacherMapper);
    }

    @Override
    public Teacher getById(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        List<Teacher> teachers = jdbcTemplate.query(GET_BY_ID, namedParameters, teacherMapper);
        if (teachers.isEmpty()) {
            return new Teacher();
        }
        return teachers.get(0);
    }

    @Override
    public Teacher insert(Teacher item) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("first_name", item.getFirstName())
                .addValue("last_name", item.getLastName())
                .addValue("gender", item.getGender().getValue())
                .addValue("birthdate", Date.valueOf(item.getBirthdate()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT, namedParameters, keyHolder, new String[] { "id" });
        return new Teacher(keyHolder.getKeyAs(Integer.class), item.getFirstName(), item.getLastName(), item.getGender(),
                item.getBirthdate());
    }

    @Override
    public int update(Teacher item) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", item.getId())                
                .addValue("first_name", item.getFirstName())
                .addValue("last_name", item.getLastName())
                .addValue("gender", item.getGender().getValue())
                .addValue("birthdate", Date.valueOf(item.getBirthdate()));
        return jdbcTemplate.update(UPDATE, namedParameters);
    }

    @Override
    public int delete(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return jdbcTemplate.update(DELETE, namedParameters);
    }
}
