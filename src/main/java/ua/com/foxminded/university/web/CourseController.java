package ua.com.foxminded.university.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.service.CourseService;

@RequestMapping("/courses")
@Controller
public class CourseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    private CourseService courseService;

    @GetMapping
    public String list(Model model) {
        LOGGER.debug("Listing courses");
        List<Course> courses = courseService.getAll();
        model.addAttribute("courses", courses);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("No. of courses: {}", courses.size());
        }
        return "courses/list";
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
