package ua.com.foxminded.university.dao.jdbc;

import static ua.com.foxminded.university.dao.jdbc.Query.PERIOD_GET_ALL;
import static ua.com.foxminded.university.dao.jdbc.Query.PERIOD_GET_BY_ID;
import static ua.com.foxminded.university.dao.jdbc.Query.PERIOD_INSERT;
import static ua.com.foxminded.university.dao.jdbc.Query.PERIOD_UPDATE;
import static ua.com.foxminded.university.dao.jdbc.Query.PERIOD_DELETE;

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
import ua.com.foxminded.university.exception.DaoException;
import ua.com.foxminded.university.model.Period;

@Repository
public class PeriodDaoJdbc extends AbstractDAO implements PeriodDao {    
    private PeriodMapper periodMapper;

    @Autowired
    public void setPeriodMapper(PeriodMapper periodMapper) {
        this.periodMapper = periodMapper;
    }

    @Override
    public List<Period> getAll() {
        try {
            return jdbcTemplate.query(PERIOD_GET_ALL, periodMapper);
        } catch (Exception e) {
            throw new DaoException("Cannot get all periods", e);
        }  
    }

    @Override
    public Period getById(int id) { 
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
            List<Period> periods = jdbcTemplate.query(PERIOD_GET_BY_ID, namedParameters, periodMapper);
            if (periods.isEmpty()) {
                return new Period();
            }
            return periods.get(0);
        } catch (Exception e) {

            throw new DaoException("Cannot get period by id. id = " + id, e);
        }
    }

    @Override
    public Period insert(Period item) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("name", item.getName())
                    .addValue("start_time", Time.valueOf(item.getStart()))
                    .addValue("end_time", Time.valueOf(item.getEnd()));
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(PERIOD_INSERT, namedParameters, keyHolder, new String[] { "id" });
            return new Period(keyHolder.getKeyAs(Integer.class), item.getName(), item.getStart(), item.getEnd());
        } catch (Exception e) {            
            throw new DaoException("Cannot create period. " + item, e);
        }
    }

    @Override
    public int update(Period item) {        
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("id", item.getId())
                    .addValue("name", item.getName())
                    .addValue("start_time", Time.valueOf(item.getStart()))
                    .addValue("end_time", Time.valueOf(item.getEnd()));
            return jdbcTemplate.update(PERIOD_UPDATE, namedParameters);
        } catch (Exception e) {
            throw new DaoException("Cannot update period. " + item, e);
        }
    }

    @Override
    public int delete(int id) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
            return jdbcTemplate.update(PERIOD_DELETE, namedParameters);
        } catch (Exception e) {            
            throw new DaoException("Cannot remove classroom. id = " + id, e);
        }
    }
}
