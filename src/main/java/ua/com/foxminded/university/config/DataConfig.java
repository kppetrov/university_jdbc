package ua.com.foxminded.university.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiTemplate;

@Configuration
@PropertySource("classpath:db/jndi.properties")
@ComponentScan(basePackages = { "ua.com.foxminded.university.dao", "ua.com.foxminded.university.service" })
public class DataConfig {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Bean(destroyMethod = "")
    public DataSource dataSource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(jdbcUrl);
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() throws NamingException {
        return new NamedParameterJdbcTemplate(dataSource());
    }
}
