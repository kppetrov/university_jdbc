package ua.com.foxminded.university.web;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.com.foxminded.university.config.WebConfig;
import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.service.CourseService;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
class CourseControllerTest {
    private MockMvc mockMvc;
    @Mock
    private CourseService courseService;
    @InjectMocks
    private CourseController controller;

    private Course course = new Course(1, "course"); 

    @BeforeEach
    public void beforeEach() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetAll() throws Exception {
        List<Course> expected = Arrays.asList(course);
        when(courseService.getAll()).thenReturn(expected);
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses/list"))
                .andExpect(model().attributeExists("courses"))
                .andExpect(model().attribute("courses", expected));
        verify(courseService, times(1)).getAll();
        verifyNoMoreInteractions(courseService);
    }
}
