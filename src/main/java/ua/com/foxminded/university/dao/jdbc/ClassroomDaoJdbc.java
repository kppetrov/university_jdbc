package ua.com.foxminded.university.dao.jdbc;

import static ua.com.foxminded.university.dao.jdbc.Query.CLASSROOM_GET_ALL;
import static ua.com.foxminded.university.dao.jdbc.Query.CLASSROOM_GET_BY_ID;
import static ua.com.foxminded.university.dao.jdbc.Query.CLASSROOM_INSERT;
import static ua.com.foxminded.university.dao.jdbc.Query.CLASSROOM_UPDATE;
import static ua.com.foxminded.university.dao.jdbc.Query.CLASSROOM_DELETE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.ClassroomDao;
import ua.com.foxminded.university.dao.DaoException;
import ua.com.foxminded.university.dao.jdbc.mappers.ClassroomMapper;
import ua.com.foxminded.university.model.Classroom;

@Repository
public class ClassroomDaoJdbc extends AbstractDAO implements ClassroomDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassroomDaoJdbc.class); 
    
    private ClassroomMapper classroomMapper;

    @Autowired
    public void setClassroomMapper(ClassroomMapper classroomMapper) {
        this.classroomMapper = classroomMapper;
    }

    @Override
    public List<Classroom> getAll() {
        try {
            return jdbcTemplate.query(CLASSROOM_GET_ALL, classroomMapper);
        } catch (Exception e) {
            String msg = "Cannot get all classrooms";
            LOGGER.error(msg, e);
            throw new DaoException(msg, e);
        }        
    }

    @Override
    public Classroom getById(int id) {        
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
            List<Classroom> classrooms = jdbcTemplate.query(CLASSROOM_GET_BY_ID, namedParameters, classroomMapper);
            if (classrooms.isEmpty()) {
                return new Classroom();
            }
            return classrooms.get(0);
        } catch (Exception e) {
            String msg = "Cannot get classroom by id. Id = " + id;
            LOGGER.error(msg, e);
            throw new DaoException(msg, e);
        }
    }

    @Override
    public Classroom insert(Classroom item) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource("name", item.getName());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(CLASSROOM_INSERT, namedParameters, keyHolder, new String[] { "id" });
            return new Classroom(keyHolder.getKeyAs(Integer.class), item.getName());
        } catch (Exception e) {            
            String msg = "Cannot create classroom. " + item;
            LOGGER.error(msg, e);
            throw new DaoException(msg, e);
        }
    }

    @Override
    public int update(Classroom item) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", item.getId()).addValue("name",
                    item.getName());
            return jdbcTemplate.update(CLASSROOM_UPDATE, namedParameters);
        } catch (Exception e) {
            String msg = "Cannot update classroom. " + item;
            LOGGER.error(msg, e);
            throw new DaoException(msg, e);
        }
    }

    @Override
    public int delete(int id) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
            return jdbcTemplate.update(CLASSROOM_DELETE, namedParameters);
        } catch (Exception e) {            
            String msg = "Cannot remove classroom. Id = " + id;
            LOGGER.error(msg, e);
            throw new DaoException(msg, e);

        }
    }
}
