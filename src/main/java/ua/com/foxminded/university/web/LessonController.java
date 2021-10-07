package ua.com.foxminded.university.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.service.LessonService;

@RequestMapping("/lessons")
@Controller
public class LessonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonController.class);

    private LessonService lessonService;

    @GetMapping
    public String list(Model model) {
        LOGGER.debug("Listing lessons");
        List<Lesson> lessons = lessonService.getAll();
        model.addAttribute("lessons", lessons);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("No. of lessons: {}", lessons.size());
        }
        return "lessons/list";
    }

    @Autowired
    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }
}
