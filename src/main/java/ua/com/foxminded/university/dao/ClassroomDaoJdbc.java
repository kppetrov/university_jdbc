package ua.com.foxminded.university.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.model.Classroom;

@Repository
public class ClassroomDaoJdbc extends AbstractDAO implements ClassroomDao {
    private static final String GET_ALL = "select id, name from classrooms";
    private static final String GET_BY_ID = "select id, name from classrooms where id = ?";
    private static final String INSERT = "insert into classrooms (name) values (?)";
    private static final String UPDATE = "update classrooms set name = ? where id = ?";
    private static final String DELETE = "delete from classrooms where id = ?";

    @Override
    public List<Classroom> getAll() {
        return jdbcTemplate.query(GET_ALL, classroomRowMapper);
    }

    @Override
    public Classroom getById(int id) {
        List<Classroom> classrooms = jdbcTemplate.query(GET_BY_ID, classroomRowMapper, id);
        if (classrooms.isEmpty()) {
            return new Classroom();
        }
        return classrooms.get(0);
    }

    @Override
    public Classroom insert(Classroom item) {
        jdbcTemplate.update(INSERT, item.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
            ps.setString(1, item.getName());
            return ps;
        }, keyHolder);

        return new Classroom(keyHolder.getKeyAs(Integer.class), item.getName());
    }

    @Override
    public int update(Classroom item) {
        return jdbcTemplate.update(UPDATE, item.getName(), item.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(DELETE, id);
    }

    private final RowMapper<Classroom> classroomRowMapper = (resultSet, rowNum) -> {
        Classroom classroom = new Classroom();
        classroom.setId(resultSet.getInt("id"));
        classroom.setName(resultSet.getString("name"));
        return classroom;
    };
}
