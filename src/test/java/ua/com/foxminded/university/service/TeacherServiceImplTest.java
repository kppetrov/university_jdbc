package ua.com.foxminded.university.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.model.Gender;
import ua.com.foxminded.university.model.Teacher;

@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {
    @Mock
    private TeacherDao teacherDao;
    @InjectMocks
    private TeacherServiceImpl service;
    
    private Teacher teacher1 = new Teacher(1, "first_name1", "last_name1", Gender.MAIL, LocalDate.of(1971, 01, 01));

    @Test
    void testGetAll() {
        service.getAll();
        verify(teacherDao, times(1)).getAll();
    }

    @Test
    void testGetById() {
        int id = 1;
        service.getById(id);
        verify(teacherDao, times(1)).getById(id);
    }

    @Test
    void testInsert() {
        service.insert(teacher1);
        verify(teacherDao, times(1)).insert(teacher1);
    }

    @Test
    void testUpdate() {
        service.update(teacher1);
        verify(teacherDao, times(1)).update(teacher1);
    }

    @Test
    void testDelete() {
        int id = 1;
        service.delete(id);
        verify(teacherDao, times(1)).delete(id);
    }
}
