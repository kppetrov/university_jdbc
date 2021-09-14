package ua.com.foxminded.university.dao.jdbc;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dao.jdbc.mappers.CourseMapper;
import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Group;

@Repository
public class CourseDaoJdbc extends AbstractDAO implements CourseDao {
    private static final String GET_ALL = "select id as course_id, name as course_name from courses";
    private static final String GET_BY_ID = "select id as course_id, name as course_name from courses where id = :id";
    private static final String INSERT = "insert into courses (name) values (:name)";
    private static final String UPDATE = "update courses set name = :name where id = :id";
    private static final String DELETE = "delete from courses where id = :id";    
    private static final String REMOVE_GROUP_FROM_COURSE = "delete from course_group where course_id = :course_id and not group_id in(:group_ids)";
    private static final String ADD_GROUP_TO_COURSE = 
            "insert into course_group (course_id, group_id) "
            + "select :course_id, g.id "
            + "from groups as g "
            + "left join course_group as cg "
            + "on g.id = cg.group_id and cg.course_id = :course_id "
            + "where g.id in (:group_ids) and cg.group_id is null";

    private CourseMapper courseMapper;
    private LessonDao lessonDao;
    private GroupDao groupDao;   
    
    @Autowired
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }
    
    @Autowired
    public void setLessonDao(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }
    
    @Autowired
    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }
    
    @Override
    public List<Course> getAll() {
        return jdbcTemplate.query(GET_ALL, courseMapper);
    }

    @Override
    public Course getById(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        List<Course> courses = jdbcTemplate.query(GET_BY_ID, namedParameters, courseMapper);
        if (courses.isEmpty()) {
            return new Course();
        }
        return courses.get(0);
    }
    
    @Override
    public Course getByIdWithDetail(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        List<Course> courses = jdbcTemplate.query(GET_BY_ID, namedParameters, courseMapper);
        if (courses.isEmpty()) {
            return new Course();
        }
        Course course = courses.get(0);
        course.setGroups(groupDao.getByCourseId(id));
        course.setLessons(lessonDao.getByCourseId(id));
        return course;     
    }

    @Override
    public Course insert(Course item) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("name", item.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT, namedParameters, keyHolder, new String[] { "id" });
        Course course =  new Course();
        course.setId(keyHolder.getKeyAs(Integer.class));
        course.setName(item.getName());
        return course;
    }

    @Override
    public int update(Course item) {
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
    public int updateGroups(Course item) {
        int result = 0;    
        List<Integer> groupIds = item.getGroups().stream().map(Group::getId).collect(Collectors.toList());        
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("course_id", item.getId())
                .addValue("group_ids", groupIds);

        result += jdbcTemplate.update(REMOVE_GROUP_FROM_COURSE, namedParameters);
        result += jdbcTemplate.update(ADD_GROUP_TO_COURSE, namedParameters);        
        return result;
    }    
}
