package ua.com.foxminded.university.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.model.Student;

@Component
public class GroupWithStudentsExtractor implements ResultSetExtractor<Group> {
    private StudentMapper studentMapper;

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public Group extractData(ResultSet rs) throws SQLException, DataAccessException {
        Group group = new Group();

        while (rs.next()) {
            if (group.getId() == 0) {
                group.setId(rs.getInt("group_id"));
                group.setName(rs.getString("group_name"));   
            }
            int studentId = rs.getInt("student_id");
            if (studentId > 0) {
                Student student = studentMapper.mapRow(rs, 0);
                group.addStudent(student);
            }
        }
        return group;
    }
}
