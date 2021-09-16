package ua.com.foxminded.university.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.model.Course;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
    @Mock
    private CourseDao courseDao;
    @InjectMocks
    private CourseServiceImpl service;

    private Course course1 = new Course(1, "course1");    
    
    @Test
    void testGetAll() {
        service.getAll();
        verify(courseDao, times(1)).getAll();
    }

    @Test
    void testGetById() {
        int id = 1;
        service.getById(id);
        verify(courseDao, times(1)).getById(id);
    }

    @Test
    void testInsert() {
        service.insert(course1);
        verify(courseDao, times(1)).insert(course1);
    }

    @Test
    void testUpdate() {
        service.update(course1);
        verify(courseDao, times(1)).update(course1);
    }

    @Test
    void testDelete() {
        int id = 1;
        service.delete(id);
        verify(courseDao, times(1)).delete(id);
    }
    

    @Test
    void testGetByIdWithDetail() {       
        int id = 1;
        service.getByIdWithDetail(id);
        verify(courseDao, times(1)).getByIdWithDetail(id);
    }

    @Test
    void testUpdateGroups() {
        service.updateGroups(course1);
        verify(courseDao, times(1)).updateGroups(course1); 
    }
}
