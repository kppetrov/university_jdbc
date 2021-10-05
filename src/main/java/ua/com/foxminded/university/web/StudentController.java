package ua.com.foxminded.university.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.university.model.Student;
import ua.com.foxminded.university.service.StudentService;

@RequestMapping("/students")
@Controller
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private StudentService studentService;

    @GetMapping
    public String list(Model model) {
        LOGGER.info("Listing students");
        List<Student> students = studentService.getAll();
        model.addAttribute("students", students);
        LOGGER.info("No. of students: {}", students.size());
        return "students/list";
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }    
}
