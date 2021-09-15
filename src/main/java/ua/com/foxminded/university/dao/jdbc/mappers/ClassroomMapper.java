package ua.com.foxminded.university.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.com.foxminded.university.model.Classroom;

@Component
public class ClassroomMapper implements RowMapper<Classroom> {
    @Override
    public Classroom mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Classroom classroom = new Classroom();
        classroom.setId(resultSet.getInt("classroom_id"));
        classroom.setName(resultSet.getString("classroom_name"));
        return classroom;
    }
}
