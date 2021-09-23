package ua.com.foxminded.university.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.model.Group;

@Service
public class GroupServiceImpl implements GroupService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupServiceImpl.class);    
    private static final String NAME_IS_TAKEN = "Cannot create a group. A group with this name(%s) already exists";

    private GroupDao groupDao;

    @Autowired
    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public List<Group> getAll() {
        try {
            return groupDao.getAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Group getById(int id) {
        try {
            return groupDao.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Group getByName(String name) {
        try {
            return groupDao.getByName(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Group insert(Group item) {
        if (nameIsTaken(item.getName())) {
            String msg = String.format(NAME_IS_TAKEN, item.getName());
            LOGGER.info(msg);
            throw new ServiceException(msg);
        }
        try {
            return groupDao.insert(item);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private boolean nameIsTaken(String name) {
        try {
            Group group = groupDao.getByName(name);
            return group.getId() > 0;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int update(Group item) {
        try {
            return groupDao.update(item);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int delete(int id) {
        try {
            return groupDao.delete(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Group getByIdWithDetail(int id) {
        try {
            return groupDao.getByIdWithDetail(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Group> getByCourseId(int curseId) {
        try {
            return groupDao.getByCourseId(curseId);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int updateStudents(Group item) {
        try {
            return groupDao.updateStudents(item);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
