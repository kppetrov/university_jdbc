package ua.com.foxminded.university.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.service.TeacherService;

@RequestMapping("/teachers")
@Controller
public class TeacherController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);

    private TeacherService teacherService;

    @GetMapping
    public String list(Model model) {
        LOGGER.info("Listing teachers");
        List<Teacher> teachers = teacherService.getAll();
        model.addAttribute("teachers", teachers);
        LOGGER.info("No. of teachers: {}", teachers.size());
        return "teachers/list";
    }

    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }    
}
