package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.model.Course;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }

    @Override
    public Course getById(int id) {
        return courseDao.getById(id);
    }

    @Override
    public Course insert(Course item) {
        return courseDao.insert(item);
    }

    @Override
    public int update(Course item) {
        return courseDao.update(item);
    }

    @Override
    public int delete(int id) {
        return courseDao.delete(id);
    }

    @Override
    public Course getByIdWithDetail(int id) {
        return courseDao.getByIdWithDetail(id);
    }

    @Override
    public int updateGroups(Course item) {
        return courseDao.updateGroups(item);
    }
}
