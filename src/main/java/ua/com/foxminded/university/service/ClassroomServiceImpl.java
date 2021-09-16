package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.ClassroomDao;
import ua.com.foxminded.university.model.Classroom;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    private ClassroomDao classroomDao;
    
    @Autowired
    public void setClassroomDao(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }

    @Override
    public List<Classroom> getAll() {
        return classroomDao.getAll();
    }

    @Override
    public Classroom getById(int id) {
        return classroomDao.getById(id);
    }

    @Override
    public Classroom insert(Classroom item) {
        return classroomDao.insert(item);
    }

    @Override
    public int update(Classroom item) {
        return classroomDao.update(item);
    }

    @Override
    public int delete(int id) {
        return classroomDao.delete(id);
    }
}
