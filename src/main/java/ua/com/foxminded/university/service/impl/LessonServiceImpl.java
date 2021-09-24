package ua.com.foxminded.university.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.exception.ServiceException;
import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {
    private static final String TEACHER_IS_BUSY = "Cannot create lesson. The teacher is busy at this time.";
    private static final String CLASSROOM_IS_OCCUPIED = "Cannot create lesson. The classroom is occupied at this time.";
    private static final String TEACHER_IS_BUSY_UPDATE = "Cannot update lesson. The teacher is busy at this time.";
    private static final String CLASSROOM_IS_OCCUPIED_UPDATE = "Cannot update lesson. The classroom is occupied at this time.";
    
    private LessonDao lessonDao;

    @Autowired
    public void setLessonDao(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public List<Lesson> getAll() {
        try {
            return lessonDao.getAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Lesson getById(int id) {
        try {
            return lessonDao.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Lesson insert(Lesson item) {
        if (teacherIsBusy(item.getDate(), item.getPeriod().getId(), item.getTeacher().getId())) {
            throw new ServiceException(TEACHER_IS_BUSY);        }
        if (classroomIsOccupied(item.getDate(), item.getPeriod().getId(), item.getClassroom().getId())) {
            throw new ServiceException(CLASSROOM_IS_OCCUPIED);
        }
        try {
            return lessonDao.insert(item);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int update(Lesson item) {
        if (teacherIsBusy(item.getDate(), item.getPeriod().getId(), item.getTeacher().getId(), item.getId())) {
            throw new ServiceException(TEACHER_IS_BUSY_UPDATE);
        }
        if (classroomIsOccupied(item.getDate(), item.getPeriod().getId(), item.getClassroom().getId(), item.getId())) {
            throw new ServiceException(CLASSROOM_IS_OCCUPIED_UPDATE);
        }
        try {
            return lessonDao.update(item);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private boolean teacherIsBusy(LocalDate date, int periodId, int teacherId, int exceptLessonId) {
        try {
            Lesson lesson = lessonDao.getByDatePeriodIdTeacherId(date, periodId, teacherId);
            return lesson.getId() > 0 && lesson.getId() != exceptLessonId;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private boolean classroomIsOccupied(LocalDate date, int periodId, int classroomId, int exceptLessonId) {
        try {
            Lesson lesson = lessonDao.getByDatePeriodIdClassroomId(date, periodId, classroomId);
            return lesson.getId() > 0 && lesson.getId() != exceptLessonId;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private boolean teacherIsBusy(LocalDate date, int periodId, int teacherId) {
        return teacherIsBusy(date, periodId, teacherId, -1);
    }

    private boolean classroomIsOccupied(LocalDate date, int periodId, int classroomId) {
        return classroomIsOccupied(date, periodId, classroomId, -1);
    }

    @Override
    public int delete(int id) {
        try {
            return lessonDao.delete(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Lesson> getByCourseId(int curseId) {
        try {
            return lessonDao.getByCourseId(curseId);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Lesson> getByGroupId(int groupId) {
        try {
            return lessonDao.getByGroupId(groupId);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Lesson> getByTeacherId(int teacherId) {
        try {
            return lessonDao.getByTeacherId(teacherId);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
