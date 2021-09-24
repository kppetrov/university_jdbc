package ua.com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.exception.ServiceException;
import ua.com.foxminded.university.model.Student;
import ua.com.foxminded.university.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;   
    
    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        try {
            this.studentDao = studentDao;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Student> getAll() {
        try {
            return studentDao.getAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Student getById(int id) {
        try {
            return studentDao.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Student insert(Student item) {
        try {
            return studentDao.insert(item);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int update(Student item) {
        try {
            return studentDao.update(item);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int delete(int id) {
        try {
            return studentDao.delete(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
