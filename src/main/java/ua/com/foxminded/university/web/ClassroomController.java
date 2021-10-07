package ua.com.foxminded.university.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.university.model.Classroom;
import ua.com.foxminded.university.service.ClassroomService;

@RequestMapping("/classrooms")
@Controller
public class ClassroomController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassroomController.class);
    
    private ClassroomService classroomService;
    
    @GetMapping
    public String list(Model model) {
        LOGGER.debug("Listing classrooms");
        List<Classroom> classrooms = classroomService.getAll();
        model.addAttribute("classrooms", classrooms);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("No. of classrooms: {}", classrooms.size());
        }
        return "classrooms/list";
    }    
    
    @Autowired
    public void setClassroomService(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }
}
