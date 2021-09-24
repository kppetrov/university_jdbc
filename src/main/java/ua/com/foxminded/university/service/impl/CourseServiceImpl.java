package ua.com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.exception.ServiceException;
import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> getAll() {
        try {
            return courseDao.getAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Course getById(int id) {
        try {
            return courseDao.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Course insert(Course item) {
        try {
            return courseDao.insert(item);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int update(Course item) {
        try {
            return courseDao.update(item);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int delete(int id) {
        try {
            return courseDao.delete(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Course getByIdWithDetail(int id) {
        try {
            return courseDao.getByIdWithDetail(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int updateGroups(Course item) {
        try {
            return courseDao.updateGroups(item);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
