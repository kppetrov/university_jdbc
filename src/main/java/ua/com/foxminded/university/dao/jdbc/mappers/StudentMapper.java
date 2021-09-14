package ua.com.foxminded.university.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.com.foxminded.university.model.Gender;
import ua.com.foxminded.university.model.Student;

@Component
public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("student_id"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setGender(Gender.of(resultSet.getString("gender")));
        student.setBirthdate(resultSet.getDate("birthdate").toLocalDate());
        return student;
    }
}
