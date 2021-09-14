package ua.com.foxminded.university.dao.jdbc;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.PeriodDao;
import ua.com.foxminded.university.dao.jdbc.mappers.PeriodMapper;
import ua.com.foxminded.university.model.Period;

@Repository
public class PeriodDaoJdbc extends AbstractDAO implements PeriodDao {
    private static final String GET_ALL = "select id as period_id, name as period_name, start_time, end_time from periods";
    private static final String GET_BY_ID = "select id as period_id, name as period_name, start_time, end_time from periods where id = :id";
    private static final String INSERT = "insert into periods (name, start_time, end_time) values (:name, :start_time, :end_time)";
    private static final String UPDATE = "update periods set name = :name, start_time = :start_time, end_time = :end_time where id = :id";
    private static final String DELETE = "delete from periods where id = :id";
    
    private PeriodMapper periodMapper;

    @Autowired
    public void setPeriodMapper(PeriodMapper periodMapper) {
        this.periodMapper = periodMapper;
    }

    @Override
    public List<Period> getAll() {
        return jdbcTemplate.query(GET_ALL, periodMapper);
    }

    @Override
    public Period getById(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        List<Period> periods = jdbcTemplate.query(GET_BY_ID, namedParameters, periodMapper);
        if (periods.isEmpty()) {
            return new Period();
        }
        return periods.get(0);
    }

    @Override
    public Period insert(Period item) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", item.getName())
                .addValue("start_time", Time.valueOf(item.getStart()))
                .addValue("end_time", Time.valueOf(item.getEnd()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT, namedParameters, keyHolder, new String[] { "id" });
        return new Period(keyHolder.getKeyAs(Integer.class), item.getName(), item.getStart(), item.getEnd());
    }

    @Override
    public int update(Period item) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", item.getId())
                .addValue("name", item.getName())
                .addValue("start_time", Time.valueOf(item.getStart()))
                .addValue("end_time", Time.valueOf(item.getEnd()));
        return jdbcTemplate.update(UPDATE, namedParameters);
    }

    @Override
    public int delete(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return jdbcTemplate.update(DELETE, namedParameters);
    }
}
