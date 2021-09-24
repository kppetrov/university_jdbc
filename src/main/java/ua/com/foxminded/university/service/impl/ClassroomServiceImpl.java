package ua.com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.ClassroomDao;
import ua.com.foxminded.university.exception.ServiceException;
import ua.com.foxminded.university.model.Classroom;
import ua.com.foxminded.university.service.ClassroomService;

@Service
public class ClassroomServiceImpl implements ClassroomService {  
    private ClassroomDao classroomDao;
    
    @Autowired
    public void setClassroomDao(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }

    @Override
    public List<Classroom> getAll() {
        try {
            return classroomDao.getAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Classroom getById(int id) {
        try {
            return classroomDao.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Classroom insert(Classroom item) {
        try {
            return classroomDao.insert(item);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int update(Classroom item) {
        try {
            return classroomDao.update(item);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int delete(int id) {
        try {
            return classroomDao.delete(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
