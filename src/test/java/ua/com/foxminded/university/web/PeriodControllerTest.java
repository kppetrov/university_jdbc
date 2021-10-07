package ua.com.foxminded.university.web;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalTime;
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
import ua.com.foxminded.university.model.Period;
import ua.com.foxminded.university.service.PeriodService;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
class PeriodControllerTest {
    private MockMvc mockMvc;
    @Mock
    private PeriodService periodService;
    @InjectMocks
    private PeriodController controller;

    private Period period = new Period(1, "period", LocalTime.of(8, 0), LocalTime.of(9, 30));

    @BeforeEach
    public void beforeEach() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetAll() throws Exception {
        List<Period> expected = Arrays.asList(period);
        when(periodService.getAll()).thenReturn(expected);
        mockMvc.perform(get("/periods"))
                .andExpect(status().isOk())
                .andExpect(view().name("periods/list"))
                .andExpect(model().attributeExists("periods"))
                .andExpect(model().attribute("periods", expected));
        verify(periodService, times(1)).getAll();
        verifyNoMoreInteractions(periodService);
    }
}
