package ua.com.foxminded.university.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.model.Gender;
import ua.com.foxminded.university.model.Student;

@Repository
public class StudentDaoJdbc extends AbstractDAO implements StudentDao {
    private static final String GET_ALL = "select id, first_name, last_name, gender, birthdate from students";
    private static final String GET_BY_ID = "select id, first_name, last_name, gender, birthdate from students where id = ?";
    private static final String INSERT = "insert into students (first_name, last_name, gender, birthdate) values (?, ?, ?, ?)";
    private static final String UPDATE = "update students set first_name = ?, last_name = ?, gender = ?, birthdate = ? where id = ?";
    private static final String DELETE = "delete from students where id = ?";

    @Override
    public List<Student> getAll() {
        return jdbcTemplate.query(GET_ALL, studentRowMapper);
    }

    @Override
    public Student getById(int id) {
        List<Student> students = jdbcTemplate.query(GET_BY_ID, studentRowMapper, id);
        if (students.isEmpty()) {
            return new Student();
        }
        return students.get(0);
    }

    @Override
    public Student insert(Student item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
            ps.setString(1, item.getFirstName());
            ps.setString(2, item.getLastName());
            ps.setString(3, item.getGender().getValue());
            ps.setDate(4, Date.valueOf(item.getBirthdate()));
            return ps;
        }, keyHolder);

        return new Student(keyHolder.getKeyAs(Integer.class), item.getFirstName(), item.getLastName(), item.getGender(),
                item.getBirthdate());
    }

    @Override
    public int update(Student item) {
        return jdbcTemplate.update(UPDATE, item.getFirstName(), item.getLastName(), item.getGender().getValue(),
                item.getBirthdate(), item.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(DELETE, id);
    }

    private final RowMapper<Student> studentRowMapper = (resultSet, rowNum) -> {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setGender(Gender.of(resultSet.getString("gender")));
        student.setBirthdate(resultSet.getDate("birthdate").toLocalDate());
        return student;
    };
}
