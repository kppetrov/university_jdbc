package ua.com.foxminded.university.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.model.Group;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {
    @Mock
    private GroupDao groupDao;
    @InjectMocks
    private GroupServiceImpl service;
    
    private Group group1 = new Group(1, "group1");

    @Test
    void testGetAll() {
        service.getAll();
        verify(groupDao, times(1)).getAll();
    }

    @Test
    void testGetById() {
        int id = 1;
        service.getById(id);
        verify(groupDao, times(1)).getById(id);
    }

    @Test
    void testInsert() {
        service.insert(group1);
        verify(groupDao, times(1)).insert(group1);
    }

    @Test
    void testUpdate() {
        service.update(group1);
        verify(groupDao, times(1)).update(group1);
    }

    @Test
    void testDelete() {
        int id = 1;
        service.delete(id);
        verify(groupDao, times(1)).delete(id);
    }

    @Test
    void testGetByIdWithDetail() {
        int id = 1;
        service.getByIdWithDetail(id);;
        verify(groupDao, times(1)).getByIdWithDetail(id);
    }

    @Test
    void testGetByCourseId() {
        int id = 1;
        service.getByCourseId(id);;
        verify(groupDao, times(1)).getByCourseId(id);
    }

    @Test
    void testUpdateStudents() {
        service.updateStudents(group1);
        verify(groupDao, times(1)).updateStudents(group1);
    }
}
