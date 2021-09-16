package ua.com.foxminded.university.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.model.Gender;
import ua.com.foxminded.university.model.Student;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    private StudentDao studentDao;
    @InjectMocks
    private StudentServiceImpl service;
    
    private Student student1 = new Student(1, "first_name1", "last_name1", Gender.MAIL, LocalDate.of(2001, 01, 01));
    
    @Test
    void testGetAll() {
        service.getAll();
        verify(studentDao, times(1)).getAll();
    }

    @Test
    void testGetById() {
        int id = 1;
        service.getById(id);
        verify(studentDao, times(1)).getById(id);
    }

    @Test
    void testInsert() {
        service.insert(student1);
        verify(studentDao, times(1)).insert(student1);
    }

    @Test
    void testUpdate() {
        service.update(student1);
        verify(studentDao, times(1)).update(student1);
    }

    @Test
    void testDelete() {
        int id = 1;
        service.delete(id);
        verify(studentDao, times(1)).delete(id);
    }
}
