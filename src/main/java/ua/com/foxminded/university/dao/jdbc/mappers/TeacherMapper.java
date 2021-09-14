package ua.com.foxminded.university.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.com.foxminded.university.model.Gender;
import ua.com.foxminded.university.model.Teacher;

@Component
public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getInt("teacher_id"));
        teacher.setFirstName(resultSet.getString("first_name"));
        teacher.setLastName(resultSet.getString("last_name"));
        teacher.setGender(Gender.of(resultSet.getString("gender")));
        teacher.setBirthdate(resultSet.getDate("birthdate").toLocalDate());
        return teacher;
    }
}
