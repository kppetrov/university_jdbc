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
import ua.com.foxminded.university.model.Gender;
import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.service.TeacherService;
import ua.com.foxminded.university.web.model.TeacherListModel;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
class TeacherControllerTest {
    private MockMvc mockMvc;
    @Mock
    private TeacherService teacherService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private TeacherController controller;

    private Teacher teacher = new Teacher(1, "first_name", "last_name", Gender.MAIL, LocalDate.of(1971, 01, 01));
    private TeacherListModel teacherListModel = new TeacherListModel(1, "first_name", "last_name");

    @BeforeEach
    public void beforeEach() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetAll() throws Exception {
        List<Teacher> teachers = Arrays.asList(teacher);
        List<TeacherListModel> expected = Arrays.asList(teacherListModel);        
        when(teacherService.getAll()).thenReturn(teachers);
        when(modelMapper.map(teacher, TeacherListModel.class)).thenReturn(teacherListModel);
        mockMvc.perform(get("/teachers"))
                .andExpect(status().isOk())
                .andExpect(view().name("teachers/list"))
                .andExpect(model().attributeExists("teachers"))
                .andExpect(model().attribute("teachers", expected));
        verify(teacherService, times(1)).getAll();
        verifyNoMoreInteractions(teacherService);
    }
}
