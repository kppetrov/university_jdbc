package ua.com.foxminded.university.web;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.university.model.Student;
import ua.com.foxminded.university.service.GroupService;
import ua.com.foxminded.university.service.StudentService;
import ua.com.foxminded.university.web.model.GroupModel;
import ua.com.foxminded.university.web.model.StudentModel;

@RequestMapping("/students")
@Controller
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private StudentService studentService;
    private GroupService groupService;

    @GetMapping
    public String list(Model model) {
        LOGGER.debug("Listing students");
        List<StudentModel> students = studentService.getAll().stream().map(StudentModel::new).collect(Collectors.toList());
        model.addAttribute("students", students);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("No. of students: {}", students.size());
        }
        return "students/list";
    }

    @GetMapping(value = "/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Showing student. id = {}", id);
        }
        StudentModel student = new StudentModel(studentService.getById(id));
        model.addAttribute("student", student);
        return "students/show";
    }

    @GetMapping(value = "/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Editing student. id = {}", id);
        }
        StudentModel student = new StudentModel(studentService.getById(id));
        model.addAttribute("student", student);
        List<GroupModel> groups = groupService.getAll().stream().map(GroupModel::new).collect(Collectors.toList());
        model.addAttribute("groups", groups);
        return "students/form";
    }

    @GetMapping(value = "/new")
    public String createForm(Model model) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Createing student");
        }
        StudentModel student = new StudentModel(new Student());
        model.addAttribute("student", student);
        List<GroupModel> groups = groupService.getAll().stream().map(GroupModel::new).collect(Collectors.toList());
        model.addAttribute("groups", groups);
        return "students/form";
    }

    @PostMapping(value = "/update")
    public String edit(@ModelAttribute("student") StudentModel student) {
        studentService.update(student.toEntity());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Student has been updated: {}", student);
        }
        return "redirect:/students/" + student.getId();
    }

    @PostMapping(value = "/add")
    public String create(@ModelAttribute("student") StudentModel student) {
        Student newStudent = studentService.insert(student.toEntity());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Student has been created: {}", newStudent);
        }
        return "redirect:/students";
    }
    
    @GetMapping(value = "/remove/{id}")
    public String remove(@PathVariable("id") Integer id) {
        studentService.delete(id);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Student has been deleted. id = {}", id);
        }
        return "redirect:/students";
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
}
