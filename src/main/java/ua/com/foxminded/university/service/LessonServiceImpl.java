package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.model.Lesson;

@Service
public class LessonServiceImpl implements LessonService {
    private LessonDao lessonDao;

    @Autowired
    public void setLessonDao(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public List<Lesson> getAll() {
        return lessonDao.getAll();
    }

    @Override
    public Lesson getById(int id) {
        return lessonDao.getById(id);
    }

    @Override
    public Lesson insert(Lesson item) {
        return lessonDao.insert(item);
    }

    @Override
    public int update(Lesson item) {
        return lessonDao.update(item);
    }

    @Override
    public int delete(int id) {
        return lessonDao.delete(id);
    }

    @Override
    public List<Lesson> getByCourseId(int curseId) {
        return lessonDao.getByCourseId(curseId);
    }

    @Override
    public List<Lesson> getByGroupId(int groupId) {
        return lessonDao.getByGroupId(groupId);
    }

    @Override
    public List<Lesson> getByTeacherId(int teacherId) {
        return lessonDao.getByTeacherId(teacherId);
    }
}
