package ua.com.foxminded.university.dao.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import ua.com.foxminded.university.DataConfigForTesting;
import ua.com.foxminded.university.model.Period;

@SpringJUnitConfig(classes = DataConfigForTesting.class)
class PeriodDaoJdbcTest {
    @Autowired
    private PeriodDaoJdbc dao;

    private Period period1 = new Period(1, "period1", LocalTime.of(8, 0), LocalTime.of(9, 30));
    private Period period2 = new Period(2, "period2", LocalTime.of(9, 40), LocalTime.of(11, 10));

    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testGetAll() {
        List<Period> expected = new ArrayList<>();
        expected.add(period1);
        expected.add(period2);
        List<Period> actual = dao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testGetById() {
        Period actual1 = dao.getById(period1.getId());
        Period actual2 = dao.getById(period2.getId());
        assertAll(
                () -> assertEquals(period1, actual1), 
                () -> assertEquals(period2, actual2)
                );
    }

    @Test
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testInsert() {
        Period period = new Period();
        period.setName("period");
        period.setStart(LocalTime.of(11, 20));
        period.setEnd(LocalTime.of(12, 50));
        period = dao.insert(period);
        Period actual = dao.getById(period.getId());
        assertEquals(period, actual);
    }

    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdate() {
        Period period = new Period(1, "new name", LocalTime.of(11, 20), LocalTime.of(12, 50));
        int countUpdate = dao.update(period);
        Period actual = dao.getById(period.getId());
        assertAll(
                () -> assertEquals(1, countUpdate), 
                () -> assertEquals(period, actual)
                );
    }

    @Test
    @Sql(value = { "/insert-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = { "/remove-data.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void testDelete() {
        int countDelete = dao.delete(1);
        Period period = dao.getById(1);
        assertAll(
                () -> assertEquals(1, countDelete), 
                () -> assertEquals(0, period.getId())
                );
    }
}
