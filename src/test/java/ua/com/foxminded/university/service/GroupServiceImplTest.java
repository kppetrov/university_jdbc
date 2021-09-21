package ua.com.foxminded.university.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.model.Group;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {
    private static final String NAME_IS_TAKEN = "A group with this name already exists";
    
    @Mock
    private GroupDao groupDao;
    @InjectMocks
    private GroupServiceImpl service;

    private Group group = new Group(1, "group");

    @Test
    void testGetAll() {
        List<Group> groups = new ArrayList<>();
        groups.add(group);
        when(groupDao.getAll()).thenReturn(groups);
        List<Group> actual = service.getAll();
        verify(groupDao, times(1)).getAll();
        assertEquals(groups, actual);
    }

    @Test
    void testGetById() {
        when(groupDao.getById(group.getId())).thenReturn(group);
        Group actual = service.getById(group.getId());
        verify(groupDao, times(1)).getById(group.getId());
        assertEquals(group, actual);
    }

    @Test
    void testGetByName() {
        when(groupDao.getByName(group.getName())).thenReturn(group);
        Group actual = service.getByName(group.getName());
        verify(groupDao, times(1)).getByName(group.getName());
        assertEquals(group, actual);
    }

    @Test
    void testInsert() {
        when(groupDao.getByName(group.getName())).thenReturn(new Group());
        service.insert(group);
        verify(groupDao, times(1)).insert(group);
    }
    
    @Test
    void shouldThrowExceptionWhenGroupNameAlreadyExists() {
        when(groupDao.getByName(group.getName())).thenReturn(group);
        ServiceException exception = assertThrows(ServiceException.class, () -> service.insert(group));
        verify(groupDao, times(1)).getByName(group.getName());
        verify(groupDao, times(0)).insert(any());
        assertEquals(NAME_IS_TAKEN, exception.getMessage());
    }

    @Test
    void testUpdate() {
        service.update(group);
        verify(groupDao, times(1)).update(group);
    }

    @Test
    void testDelete() {
        service.delete(group.getId());
        verify(groupDao, times(1)).delete(group.getId());
    }

    @Test
    void testGetByIdWithDetail() {
        when(groupDao.getByIdWithDetail(group.getId())).thenReturn(group);
        Group actual = service.getByIdWithDetail(group.getId());
        verify(groupDao, times(1)).getByIdWithDetail(group.getId());
        assertEquals(group, actual);
    }

    @Test
    void testGetByCourseId() {
        int course_id = 1;
        List<Group> groups = new ArrayList<>();
        groups.add(group);
        when(groupDao.getByCourseId(course_id)).thenReturn(groups);
        List<Group> actual = service.getByCourseId(course_id);
        verify(groupDao, times(1)).getByCourseId(course_id);
        assertEquals(groups, actual);
    }

    @Test
    void testUpdateStudents() {
        service.updateStudents(group);
        verify(groupDao, times(1)).updateStudents(group);
    }
}
