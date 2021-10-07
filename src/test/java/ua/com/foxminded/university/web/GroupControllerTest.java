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
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.service.GroupService;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
class GroupControllerTest {
    private MockMvc mockMvc;
    @Mock
    private GroupService groupService;
    @InjectMocks
    private GroupController controller;

    private Group group = new Group(1, "group");

    @BeforeEach
    public void beforeEach() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetAll() throws Exception {
        List<Group> expected = Arrays.asList(group);
        when(groupService.getAll()).thenReturn(expected);
        mockMvc.perform(get("/groups"))
                .andExpect(status().isOk())
                .andExpect(view().name("groups/list"))
                .andExpect(model().attributeExists("groups"))
                .andExpect(model().attribute("groups", expected));
        verify(groupService, times(1)).getAll();
        verifyNoMoreInteractions(groupService);
    }
}
