package ua.com.foxminded.university.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.university.model.Period;
import ua.com.foxminded.university.service.PeriodService;

@RequestMapping("/periods")
@Controller
public class PeriodController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodController.class);

    private PeriodService periodService;

    @GetMapping
    public String list(Model model) {
        LOGGER.info("Listing periods");
        List<Period> periods = periodService.getAll();
        model.addAttribute("periods", periods);
        LOGGER.info("No. of periods: {}", periods.size());
        return "periods/list";
    }

    @Autowired
    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }
}
