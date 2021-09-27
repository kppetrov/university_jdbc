package ua.com.foxminded.university.dao.jdbc;

import static ua.com.foxminded.university.dao.jdbc.Query.GROUP_GET_ALL;
import static ua.com.foxminded.university.dao.jdbc.Query.GROUP_GET_BY_ID;
import static ua.com.foxminded.university.dao.jdbc.Query.GROUP_GET_BY_NAME;
import static ua.com.foxminded.university.dao.jdbc.Query.GROUP_GET_BY_COURSE_ID;
import static ua.com.foxminded.university.dao.jdbc.Query.GROUP_GET_BY_ID_DETAIL;
import static ua.com.foxminded.university.dao.jdbc.Query.GROUP_INSERT;
import static ua.com.foxminded.university.dao.jdbc.Query.GROUP_UPDATE;
import static ua.com.foxminded.university.dao.jdbc.Query.GROUP_DELETE;
import static ua.com.foxminded.university.dao.jdbc.Query.GROUP_REMOVE_STUDENTS_FROM_GROUP;
import static ua.com.foxminded.university.dao.jdbc.Query.GROUP_ADD_STUDENTS_TO_GROUP;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.dao.jdbc.mappers.GroupMapper;
import ua.com.foxminded.university.dao.jdbc.mappers.GroupWithStudentsExtractor;
import ua.com.foxminded.university.exception.DaoException;
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.model.Student;

@Repository
public class GroupDaoJdbc extends AbstractDAO implements GroupDao {
    private GroupMapper groupMapper;
    private GroupWithStudentsExtractor groupWithDetailExtractor;
    
    @Autowired
    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Autowired
    public void setGroupWithDetailExtractor(GroupWithStudentsExtractor groupWithDetailExtractor) {
        this.groupWithDetailExtractor = groupWithDetailExtractor;
    }

    @Override
    public List<Group> getAll() {
        try {
            return jdbcTemplate.query(GROUP_GET_ALL, groupMapper);
        } catch (DataAccessException e) {
            throw new DaoException("Cannot get all groups", e);
        }   
    }

    @Override
    public Group getById(int id) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
            List<Group> groups = jdbcTemplate.query(GROUP_GET_BY_ID, namedParameters, groupMapper);
            if (groups.isEmpty()) {
                return new Group();
            }
            return groups.get(0);
        } catch (DataAccessException e) {
            throw new DaoException("Cannot get group by id. id = " + id, e);
        }
    }
    
    @Override
    public Group getByName(String name) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource("name", name);
            List<Group> groups = jdbcTemplate.query(GROUP_GET_BY_NAME, namedParameters, groupMapper);
            if (groups.isEmpty()) {
                return new Group();
            }
            return groups.get(0);
        } catch (DataAccessException e) {            
            throw new DaoException("Cannot get group by name. Name = " + name, e);
        }
    }

    @Override
    public Group getByIdWithDetail(int id) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
            return jdbcTemplate.query(GROUP_GET_BY_ID_DETAIL, namedParameters, groupWithDetailExtractor);
        } catch (DataAccessException e) {
            throw new DaoException("Cannot get group by id with detail. Id = " + id, e);
        }
    }
    
    @Override
    public List<Group> getByCourseId(int curseId) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource("course_id", curseId);
            return jdbcTemplate.query(GROUP_GET_BY_COURSE_ID, namedParameters, groupMapper);
        } catch (DataAccessException e) {
            throw new DaoException("Cannot get group by course id. Course id = " + curseId, e);
        }
    }

    @Override
    public Group insert(Group item) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource("name", item.getName());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(GROUP_INSERT, namedParameters, keyHolder, new String[] { "id" });
            return new Group(keyHolder.getKeyAs(Integer.class), item.getName(), new ArrayList<>());
        } catch (DataAccessException e) {            
            throw new DaoException("Cannot create group. " + item, e);
        }
    }

    @Override
    public int update(Group item) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("id", item.getId())
                    .addValue("name", item.getName());
            return jdbcTemplate.update(GROUP_UPDATE, namedParameters);
        } catch (DataAccessException e) {
            throw new DaoException("Cannot update group. " + item, e);
        }
    }

    @Override
    public int delete(int id) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
            return jdbcTemplate.update(GROUP_DELETE, namedParameters);
        } catch (DataAccessException e) {            
            throw new DaoException("Cannot remove group. Id = " + id, e);
        }
    }

    
    @Override
    public int updateStudents(Group item) {
        try {
            int result = 0;    
            List<Integer> studentsIds = item.getStudents().stream().map(Student::getId).collect(Collectors.toList());        
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("group_id", item.getId())
                    .addValue("students_ids", studentsIds);
            result += jdbcTemplate.update(GROUP_REMOVE_STUDENTS_FROM_GROUP, namedParameters);
            result += jdbcTemplate.update(GROUP_ADD_STUDENTS_TO_GROUP, namedParameters);     
            return result;
        } catch (DataAccessException e) {
            throw new DaoException("Cannot update group students. " + item, e);
        }
    }
}
