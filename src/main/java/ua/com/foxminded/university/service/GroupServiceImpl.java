package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.model.Group;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupDao groupDao;

    @Autowired
    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public List<Group> getAll() {
        return groupDao.getAll();
    }

    @Override
    public Group getById(int id) {
        return groupDao.getById(id);
    }

    @Override
    public Group getByName(String name) {
        return groupDao.getByName(name);
    }
    
    @Override
    public Group insert(Group item) {
        if (nameIsOccupied(item.getName())) {
            throw new ServiceException("A group with this name already exists");
        }
        return groupDao.insert(item);
    }
    
    private boolean nameIsOccupied(String name) {
        Group group = groupDao.getByName(name);
        return group.getId() > 0;
    }

    @Override
    public int update(Group item) {
        return groupDao.update(item);
    }

    @Override
    public int delete(int id) {
        return groupDao.delete(id);
    }

    @Override
    public Group getByIdWithDetail(int id) {
        return groupDao.getByIdWithDetail(id);
    }

    @Override
    public List<Group> getByCourseId(int curseId) {
        return groupDao.getByCourseId(curseId);
    }

    @Override
    public int updateStudents(Group item) {
        return groupDao.updateStudents(item);
    }
}
