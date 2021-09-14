package ua.com.foxminded.university.dao.jdbc;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class AbstractDAO implements InitializingBean{
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null) {
            throw new BeanCreationException("Null JdbcTemplate");
        }
    } 
}
