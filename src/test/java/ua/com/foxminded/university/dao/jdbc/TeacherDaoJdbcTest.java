package ua.com.foxminded.university.dao.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import ua.com.foxminded.university.DataConfigForTesting;
import ua.com.foxminded.university.model.Gender;
import ua.com.foxminded.university.model.Teacher;

@SpringJUnitConfig(classes = DataConfigForTesting.class)
class TeacherDaoJdbcTest {
    @Autowired
    private TeacherDaoJdbc dao;
    
    private Teacher teacher1 = new Teacher(1, "first_name1", "last_name1", Gender.MAIL, LocalDate.of(1971, 01, 01));
    private Teacher teacher2 = new Teacher(2, "first_name2", "last_name2", Gender.FEMAIL, LocalDate.of(1972, 02, 02));  

    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testGetAll() {
        List<Teacher> expected = new ArrayList<>();
        expected.add(teacher1);
        expected.add(teacher2);
        List<Teacher> actual = dao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testGetById() {
        Teacher actual1 = dao.getById(teacher1.getId());
        Teacher actual2 = dao.getById(teacher2.getId());
        assertAll(
                () -> assertEquals(teacher1, actual1), 
                () -> assertEquals(teacher2, actual2)
                );
    }

    @Test
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testInsert() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("first_name");
        teacher.setLastName("last_name");
        teacher.setGender(Gender.FEMAIL);
        teacher.setBirthdate(LocalDate.of(1980, 01, 01));
        teacher = dao.insert(teacher);
        Teacher actual = dao.getById(teacher.getId());
        assertEquals(teacher, actual);
    }

    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdate() {
        Teacher teacher = new Teacher(1, "new first_name", "new last_name", Gender.MAIL, LocalDate.of(1980, 01, 01));
        int countUpdate = dao.update(teacher);
        Teacher actual = dao.getById(teacher.getId());
        assertAll(
                () -> assertEquals(1, countUpdate), 
                () -> assertEquals(teacher, actual)
                );
    }

    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testDelete() {
        int countDelete = dao.delete(1);
        Teacher teacher = dao.getById(1);
        assertAll(
                () -> assertEquals(1, countDelete), 
                () -> assertEquals(0, teacher.getId())
                );
    }
}
