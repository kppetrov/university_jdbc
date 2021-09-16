package ua.com.foxminded.university.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.university.dao.ClassroomDao;
import ua.com.foxminded.university.model.Classroom;

@ExtendWith(MockitoExtension.class)
class ClassroomServiceImplTest {
    @Mock
    private ClassroomDao classroomDao;
    @InjectMocks
    private ClassroomServiceImpl service;
    
    private Classroom classroom1 = new Classroom(1, "classroom1");

    @Test
    void testGetAll() {
        service.getAll();
        verify(classroomDao, times(1)).getAll();
    }

    @Test
    void testGetById() {
        int id = 1;
        service.getById(id);
        verify(classroomDao, times(1)).getById(id);
    }

    @Test
    void testInsert() {
        service.insert(classroom1);
        verify(classroomDao, times(1)).insert(classroom1);
    }

    @Test
    void testUpdate() {
        service.update(classroom1);
        verify(classroomDao, times(1)).update(classroom1);
    }

    @Test
    void testDelete() {
        int id = 1;
        service.delete(id);
        verify(classroomDao, times(1)).delete(id);
    }
}
