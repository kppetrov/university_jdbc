package ua.com.foxminded.university.dao.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.dao.jdbc.mappers.GroupMapper;
import ua.com.foxminded.university.dao.jdbc.mappers.GroupWithStudentsExtractor;
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.model.Student;

@Repository
public class GroupDaoJdbc extends AbstractDAO implements GroupDao {
    private static final String GET_ALL = "select g.id as group_id, g.name as group_name from groups as g ";
    private static final String GET_BY_ID = "select g.id as group_id, g.name as group_name from groups as g where g.id = :id";
    private static final String GET_BY_COURSE_ID = 
            "select "
                + "g.id as group_id, "
                + "g.name as group_name "
            + "from groups as g "
                + "left join course_group as cg "
                    + "on g.id = cg.group_id "
            + "where cg.course_id = :course_id";
    private static final String GET_BY_ID_DETAIL = 
            "select "
                + "g.id as group_id, "
                + "g.name as group_name, "
                + "s.id as student_id, "
                + "s.first_name, "
                + "s.last_name, "
                + "s.gender, "
                + "s.birthdate "
            + "from groups as g "
                + "left join students s "
                + "on g.id = s.group_id "
                + "where g.id = :id";
    private static final String INSERT = "insert into groups (name) values (:name)";
    private static final String UPDATE = "update groups set name = :name where id = :id";
    private static final String DELETE = "delete from groups where id = :id";
    private static final String REMOVE_STUDENTS_FROM_GROUP = 
            "update students "
          + "set group_id = null "
          + "where group_id is not distinct from :group_id and not id in(:students_ids)";
    private static final String ADD_STUDENTS_TO_GROUP = 
            "update students " 
          + "set group_id = :group_id " 
          + "where group_id is distinct from :group_id and id in(:students_ids)";

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
        return jdbcTemplate.query(GET_ALL, groupMapper);
    }

    @Override
    public Group getById(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        List<Group> groups = jdbcTemplate.query(GET_BY_ID, namedParameters, groupMapper);
        if (groups.isEmpty()) {
            return new Group();
        }
        return groups.get(0);
    }
    
    @Override
    public Group getByIdWithDetail(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return jdbcTemplate.query(GET_BY_ID_DETAIL, namedParameters, groupWithDetailExtractor);
    }
    
    @Override
    public List<Group> getByCourseId(int curseId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("course_id", curseId);
        return jdbcTemplate.query(GET_BY_COURSE_ID, namedParameters, groupMapper);
    }

    @Override
    public Group insert(Group item) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("name", item.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT, namedParameters, keyHolder, new String[] { "id" });
        return new Group(keyHolder.getKeyAs(Integer.class), item.getName(), new ArrayList<>());
    }

    @Override
    public int update(Group item) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", item.getId())
                .addValue("name", item.getName());
        return jdbcTemplate.update(UPDATE, namedParameters);
    }

    @Override
    public int delete(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return jdbcTemplate.update(DELETE, namedParameters);
    }

    
    @Override
    public int updateStudents(Group item) {
        int result = 0;    
        List<Integer> studentsIds = item.getStudents().stream().map(Student::getId).collect(Collectors.toList());        
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("group_id", item.getId())
                .addValue("students_ids", studentsIds);
        result += jdbcTemplate.update(REMOVE_STUDENTS_FROM_GROUP, namedParameters);
        result += jdbcTemplate.update(ADD_STUDENTS_TO_GROUP, namedParameters);     
        return result;
    }
}
