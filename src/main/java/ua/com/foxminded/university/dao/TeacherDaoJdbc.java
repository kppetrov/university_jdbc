package ua.com.foxminded.university.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.model.Gender;
import ua.com.foxminded.university.model.Teacher;

@Repository
public class TeacherDaoJdbc extends AbstractDAO implements TeacherDao {
    private static final String GET_ALL = "select id, first_name, last_name, gender, birthdate from teachers";
    private static final String GET_BY_ID = "select id, first_name, last_name, gender, birthdate from teachers where id = ?";
    private static final String INSERT = "insert into teachers (first_name, last_name, gender, birthdate) values (?, ?, ?, ?)";
    private static final String UPDATE = "update teachers set first_name = ?, last_name = ?, gender = ?, birthdate = ? where id = ?";
    private static final String DELETE = "delete from teachers where id = ?";

    @Override
    public List<Teacher> getAll() {
        return jdbcTemplate.query(GET_ALL, teacherRowMapper);
    }

    @Override
    public Teacher getById(int id) {
        List<Teacher> teachers = jdbcTemplate.query(GET_BY_ID, teacherRowMapper, id);
        if (teachers.isEmpty()) {
            return new Teacher();
        }
        return teachers.get(0);
    }

    @Override
    public Teacher insert(Teacher item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
            ps.setString(1, item.getFirstName());
            ps.setString(2, item.getLastName());
            ps.setString(3, item.getGender().getValue());
            ps.setDate(4, Date.valueOf(item.getBirthdate()));
            return ps;
        }, keyHolder);

        return new Teacher(keyHolder.getKeyAs(Integer.class), item.getFirstName(), item.getLastName(), item.getGender(),
                item.getBirthdate());
    }

    @Override
    public int update(Teacher item) {
        return jdbcTemplate.update(UPDATE, item.getFirstName(), item.getLastName(), item.getGender().getValue(),
                item.getBirthdate(), item.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(DELETE, id);
    }

    private final RowMapper<Teacher> teacherRowMapper = (resultSet, rowNum) -> {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getInt("id"));
        teacher.setFirstName(resultSet.getString("first_name"));
        teacher.setLastName(resultSet.getString("last_name"));
        teacher.setGender(Gender.of(resultSet.getString("gender")));
        teacher.setBirthdate(resultSet.getDate("birthdate").toLocalDate());
        return teacher;
    };

}
