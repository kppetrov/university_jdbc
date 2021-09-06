package ua.com.foxminded.university.dao;

import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.model.Period;

@Repository
public class PeriodDaoJdbc extends AbstractDAO implements PeriodDao {
    private static final String GET_ALL = "select id, name, start_time, end_time from periods";
    private static final String GET_BY_ID = "select id, name, start_time, end_time from periods where id = ?";
    private static final String INSERT = "insert into periods (name, start_time, end_time) values (?, ?, ?)";
    private static final String UPDATE = "update periods set name = ?, start_time = ?, end_time = ? where id = ?";
    private static final String DELETE = "delete from periods where id = ?";

    @Override
    public List<Period> getAll() {
        return jdbcTemplate.query(GET_ALL, periodRowMapper);
    }

    @Override
    public Period getById(int id) {
        List<Period> periods = jdbcTemplate.query(GET_BY_ID, periodRowMapper, id);
        if (periods.isEmpty()) {
            return new Period();
        }
        return periods.get(0);
    }

    @Override
    public Period insert(Period item) {
        jdbcTemplate.update(INSERT, item.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
            ps.setString(1, item.getName());
            ps.setTime(2, Time.valueOf(item.getStart()));
            ps.setTime(3, Time.valueOf(item.getEnd()));
            return ps;
        }, keyHolder);

        return new Period(keyHolder.getKeyAs(Integer.class), item.getName(), item.getStart(), item.getEnd());
    }

    @Override
    public int update(Period item) {
        return jdbcTemplate.update(UPDATE, item.getName(), item.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(DELETE, id);
    }

    private final RowMapper<Period> periodRowMapper = (resultSet, rowNum) -> {
        Period period = new Period();
        period.setId(resultSet.getInt("id"));
        period.setName(resultSet.getString("name"));
        period.setStart(resultSet.getTime("start_time").toLocalTime());
        period.setEnd(resultSet.getTime("end_time").toLocalTime());
        return period;
    };
}
