package ua.com.foxminded.university.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @GetMapping(value = "/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Showing classroom. id = {}", id);
        }
        Classroom classroom = classroomService.getById(id);
        model.addAttribute("classroom", classroom);
        return "classrooms/show";
    }  
    
    @GetMapping(value = "/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Editing classroom. id = {}", id);
        }
        Classroom classroom = classroomService.getById(id);
        model.addAttribute("classroom", classroom);
        return "classrooms/form";
    }
    
    @GetMapping(value = "/new")
    public String createForm(Model model) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Createing classroom");
        }
        Classroom classroom = new Classroom();
        model.addAttribute("classroom", classroom);
        return "classrooms/form";
    }
    
    @PostMapping(value = "/update")
    public String edit(@ModelAttribute("classroom") @Valid Classroom classroom, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "classrooms/form";
        }
        classroomService.update(classroom);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Classroom has been updated: {}", classroom);
        }
        return "redirect:/classrooms/" + classroom.getId();
    }
    
    @PostMapping(value = "/add")
    public String create(@ModelAttribute("classroom") @Valid Classroom classroom, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "classrooms/form";
        }
        Classroom newClassroom = classroomService.insert(classroom);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Classroom has been created: {}", newClassroom);
        }
        return "redirect:/classrooms";
    }
    
    @GetMapping(value = "/remove/{id}")
    public String remove(@PathVariable("id") Integer id) {
        classroomService.delete(id);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Classroom has been deleted. id = {}", id);
        }
        return "redirect:/classrooms";
    }
    
    @Autowired
    public void setClassroomService(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }
}
