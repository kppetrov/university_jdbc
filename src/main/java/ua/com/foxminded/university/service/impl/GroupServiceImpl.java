package ua.com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.exception.DaoException;
import ua.com.foxminded.university.exception.ServiceException;
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService { 
    private GroupDao groupDao;

    @Autowired
    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public List<Group> getAll() {
        try {
            return groupDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Group getById(int id) {
        try {
            return groupDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Group getByName(String name) {
        try {
            return groupDao.getByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Group insert(Group item) {
        try {
            return groupDao.insert(item);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int update(Group item) {
        try {
            return groupDao.update(item);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int delete(int id) {
        try {
            return groupDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Group getByIdWithDetail(int id) {
        try {
            return groupDao.getByIdWithDetail(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Group> getByCourseId(int curseId) {
        try {
            return groupDao.getByCourseId(curseId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int updateStudents(Group item) {
        try {
            return groupDao.updateStudents(item);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
