package ua.com.foxminded.university.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

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
import ua.com.foxminded.university.model.Classroom;
import ua.com.foxminded.university.service.ClassroomService;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
class ClassroomControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ClassroomService classroomService;
    @InjectMocks
    private ClassroomController controller;

    private Classroom classroom = new Classroom(1, "classroom");

    @BeforeEach
    public void beforeEach() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetAll() throws Exception {
        List<Classroom> expected = Arrays.asList(classroom);
        when(classroomService.getAll()).thenReturn(expected);
        mockMvc.perform(get("/classrooms"))
                .andExpect(status().isOk())
                .andExpect(view().name("classrooms/list"))
                .andExpect(model().attributeExists("classrooms"))
                .andExpect(model().attribute("classrooms", expected));
        verify(classroomService, times(1)).getAll();
        verifyNoMoreInteractions(classroomService);
    }
}
