package ua.com.foxminded.university.web;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.com.foxminded.university.config.WebConfig;
import ua.com.foxminded.university.model.Classroom;
import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Gender;
import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.model.Period;
import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.service.LessonService;
import ua.com.foxminded.university.web.model.LessonListModel;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
class LessonControllerTest {
    private MockMvc mockMvc;
    @Mock
    private LessonService lessonService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private LessonController controller;

    private Course course = new Course(1, "course");
    private Teacher teacher = new Teacher(1, "first_name", "last_name", Gender.MAIL, LocalDate.of(1971, 01, 01));
    private Period period = new Period(1, "period", LocalTime.of(8, 0), LocalTime.of(9, 30));
    private Classroom classroom = new Classroom(1, "classroom");
    private Lesson lesson = new Lesson(1, course, LocalDate.of(2021, 01, 01), period, teacher, classroom);
    private LessonListModel lessonListModel = new LessonListModel(lesson.getId(), course.getName(),
            LocalDate.of(2021, 01, 01), period.getName());

    @BeforeEach
    public void beforeEach() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetAll() throws Exception {
        List<Lesson> lessons = Arrays.asList(lesson);
        List<LessonListModel> expected = Arrays.asList(lessonListModel);
        when(lessonService.getAll()).thenReturn(lessons);
        when(modelMapper.map(lesson, LessonListModel.class)).thenReturn(lessonListModel);
        mockMvc.perform(get("/lessons")).andExpect(status().isOk()).andExpect(view().name("lessons/list"))
                .andExpect(model().attributeExists("lessons")).andExpect(model().attribute("lessons", expected));
        verify(lessonService, times(1)).getAll();
        verifyNoMoreInteractions(lessonService);
    }
}
