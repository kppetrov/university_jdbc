package ua.com.foxminded.university.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.model.Lesson;

@Service
public class LessonServiceImpl implements LessonService {
    private static final String TEACHER_IS_BUSY = "The teacher is busy at this time";
    private static final String CLASSROOM_IS_OCCUPIED = "The classroom is occupied at this time";
    
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
        if (teacherIsBusy(item.getDate(), item.getPeriod().getId(), item.getTeacher().getId())) {
            throw new ServiceException(TEACHER_IS_BUSY);
        }
        if (classroomIsOccupied(item.getDate(), item.getPeriod().getId(), item.getClassroom().getId())) {
            throw new ServiceException(CLASSROOM_IS_OCCUPIED);
        }
        return lessonDao.insert(item);
    }

    @Override
    public int update(Lesson item) {
        if (teacherIsBusy(item.getDate(), item.getPeriod().getId(), item.getTeacher().getId(), item.getId())) {
            throw new ServiceException(TEACHER_IS_BUSY);
        }
        if (classroomIsOccupied(item.getDate(), item.getPeriod().getId(), item.getClassroom().getId(), item.getId())) {
            throw new ServiceException(CLASSROOM_IS_OCCUPIED);
        }
        return lessonDao.update(item);
    }

    private boolean teacherIsBusy(LocalDate date, int periodId, int teacherId, int exceptLessonId) {
        Lesson lesson = lessonDao.getByDatePeriodIdTeacherId(date, periodId, teacherId);
        return lesson.getId() > 0 && lesson.getId() != exceptLessonId;
    }

    private boolean classroomIsOccupied(LocalDate date, int periodId, int classroomId, int exceptLessonId) {
        Lesson lesson = lessonDao.getByDatePeriodIdClassroomId(date, periodId, classroomId);
        return lesson.getId() > 0 && lesson.getId() != exceptLessonId;
    }

    private boolean teacherIsBusy(LocalDate date, int periodId, int teacherId) {
        return teacherIsBusy(date, periodId, teacherId, -1);
    }

    private boolean classroomIsOccupied(LocalDate date, int periodId, int classroomId) {
        return classroomIsOccupied(date, periodId, classroomId, -1);
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
