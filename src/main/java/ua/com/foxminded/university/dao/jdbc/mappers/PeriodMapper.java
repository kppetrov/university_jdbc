package ua.com.foxminded.university.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.com.foxminded.university.model.Period;

@Component
public class PeriodMapper implements RowMapper<Period> {
    @Override
    public Period mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Period period = new Period();
        period.setId(resultSet.getInt("period_id"));
        period.setName(resultSet.getString("period_name"));
        period.setStart(resultSet.getTime("start_time").toLocalTime());
        period.setEnd(resultSet.getTime("end_time").toLocalTime());
        return period;
    }
}
