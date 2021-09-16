package ua.com.foxminded.university.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.university.dao.PeriodDao;
import ua.com.foxminded.university.model.Period;

@ExtendWith(MockitoExtension.class)
class PeriodServiceImplTest {
    @Mock
    private PeriodDao periodDao;
    @InjectMocks
    private PeriodServiceImpl service;
    
    private Period period1 = new Period(1, "period1", LocalTime.of(8, 0), LocalTime.of(9, 30));

    @Test
    void testGetAll() {
        service.getAll();
        verify(periodDao, times(1)).getAll();
    }

    @Test
    void testGetById() {
        int id = 1;
        service.getById(id);
        verify(periodDao, times(1)).getById(id);
    }

    @Test
    void testInsert() {
        service.insert(period1);
        verify(periodDao, times(1)).insert(period1);
    }

    @Test
    void testUpdate() {
        service.update(period1);
        verify(periodDao, times(1)).update(period1);
    }

    @Test
    void testDelete() {
        int id = 1;
        service.delete(id);
        verify(periodDao, times(1)).delete(id);
    }
}
