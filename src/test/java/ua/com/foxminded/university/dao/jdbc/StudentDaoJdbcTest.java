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
import ua.com.foxminded.university.exception.DaoException;
import ua.com.foxminded.university.model.Gender;
import ua.com.foxminded.university.model.Student;

@SpringJUnitConfig(classes = DataConfigForTesting.class)
class StudentDaoJdbcTest {
    private static final String ID_NOT_EXIST = "The student with id=%d does not exist";
    
    @Autowired
    private StudentDaoJdbc dao;
    
    private Student student1 = new Student(1, "first_name1", "last_name1", Gender.MAIL, LocalDate.of(2001, 01, 01));
    private Student student2 = new Student(2, "first_name2", "last_name2", Gender.FEMAIL, LocalDate.of(2002, 02, 02));
    private Student student3 = new Student(3, "first_name3", "last_name3", Gender.FEMAIL, LocalDate.of(2003, 03, 03));
    private Student student4 = new Student(4, "first_name4", "last_name4", Gender.FEMAIL, LocalDate.of(2004, 04, 04));

    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testGetAll() {
        List<Student> expected = new ArrayList<>();
        expected.add(student1);
        expected.add(student2);
        expected.add(student3);
        expected.add(student4);
        List<Student> actual = dao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testGetById() {
        Student actual1 = dao.getById(student1.getId());
        Student actual2 = dao.getById(student2.getId());
        assertAll(
                () -> assertEquals(student1, actual1), 
                () -> assertEquals(student2, actual2)
                );
    }
    
    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void shouldThrowExceptionWhenStudentWithSuchIdNotExist() {
        int id = 10;
        String msg = String.format(ID_NOT_EXIST, id);        
        DaoException exception = assertThrows(DaoException.class, () -> dao.getById(id));
        assertEquals(msg, exception.getMessage());
    }

    @Test
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testInsert() {
        Student student = new Student();
        student.setFirstName("first_name");
        student.setLastName("last_name");
        student.setGender(Gender.FEMAIL);
        student.setBirthdate(LocalDate.of(1980, 01, 01));
        student = dao.insert(student);
        Student actual = dao.getById(student.getId());
        assertEquals(student, actual);
    }

    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdate() {
        Student student = new Student(1, "new first_name", "new last_name", Gender.MAIL, LocalDate.of(1980, 01, 01));
        int countUpdate = dao.update(student);
        Student actual = dao.getById(student.getId());
        assertAll(
                () -> assertEquals(1, countUpdate), 
                () -> assertEquals(student, actual)
                );
    }

    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testDelete() {
        int id = 1;
        String msg = String.format(ID_NOT_EXIST, id);
        int countDelete = dao.delete(id);        
        DaoException exception = assertThrows(DaoException.class, () -> dao.getById(id));
        assertAll(
                () -> assertEquals(1, countDelete), 
                () -> assertEquals(msg, exception.getMessage())
                );
    }
}
