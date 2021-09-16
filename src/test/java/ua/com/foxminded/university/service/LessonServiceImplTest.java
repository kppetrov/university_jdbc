package ua.com.foxminded.university.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.model.Classroom;
import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Gender;
import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.model.Period;
import ua.com.foxminded.university.model.Teacher;

@ExtendWith(MockitoExtension.class)
class LessonServiceImplTest {
    @Mock
    private LessonDao lessonDao;
    @InjectMocks
    private LessonServiceImpl service;
    
    private Course course1 = new Course(1, "course1");
    private Teacher teacher1 = new Teacher(1, "first_name1", "last_name1", Gender.MAIL, LocalDate.of(1971, 01, 01));
    private Period period1 = new Period(1, "period1", LocalTime.of(8, 0), LocalTime.of(9, 30));
    private Classroom classroom1 = new Classroom(1, "classroom1");
    private Lesson lesson1 = new Lesson(1, course1, LocalDate.of(2021, 01, 01), period1, teacher1, classroom1);

    @Test
    void testGetAll() {
        service.getAll();
        verify(lessonDao, times(1)).getAll();
    }

    @Test
    void testGetById() {
        int id = 1;
        service.getById(id);
        verify(lessonDao, times(1)).getById(id);
    }

    @Test
    void testInsert() {
        service.insert(lesson1);
        verify(lessonDao, times(1)).insert(lesson1);
    }

    @Test
    void testUpdate() {
        service.update(lesson1);
        verify(lessonDao, times(1)).update(lesson1);
    }

    @Test
    void testDelete() {
        int id = 1;
        service.delete(id);
        verify(lessonDao, times(1)).delete(id);
    }
    
    @Test
    void testGetByCourseId() {
        int id = 1;
        service.getByCourseId(id);
        verify(lessonDao, times(1)).getByCourseId(id);
    }
    
    @Test

    void testGetByGroupId() {
        int id = 1;
        service.getByGroupId(id);
        verify(lessonDao, times(1)).getByGroupId(id);
    }
    
    @Test
    void testGetByTeacherId() {
        int id = 1;
        service.getByTeacherId(id);
        verify(lessonDao, times(1)).getByTeacherId(id);
    }
}
