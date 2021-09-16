package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.model.Teacher;

@Service
public class TeacherServiceImpl implements TeacherService {
    private TeacherDao teacherDao;

    @Autowired
    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> getAll() {
        return teacherDao.getAll();
    }

    @Override
    public Teacher getById(int id) {
        return teacherDao.getById(id);
    }

    @Override
    public Teacher insert(Teacher item) {
        return teacherDao.insert(item);
    }

    @Override
    public int update(Teacher item) {
        return teacherDao.update(item);
    }

    @Override
    public int delete(int id) {
        return teacherDao.delete(id);
    }
}
