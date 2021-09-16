package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;   
    
    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }

    @Override
    public Student getById(int id) {
        return studentDao.getById(id);
    }

    @Override
    public Student insert(Student item) {
        return studentDao.insert(item);
    }

    @Override
    public int update(Student item) {
        return studentDao.update(item);
    }

    @Override
    public int delete(int id) {
        return studentDao.delete(id);
    }
}
