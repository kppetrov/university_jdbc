package ua.com.foxminded.university.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.com.foxminded.university.model.Group;

@Component
public class GroupMapper implements RowMapper<Group>{
    @Override
    public Group mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getInt("group_id"));
        group.setName(resultSet.getString("group_name"));
        return group;
    }
}
