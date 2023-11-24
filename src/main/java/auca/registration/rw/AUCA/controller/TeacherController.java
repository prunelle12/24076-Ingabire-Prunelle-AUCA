package auca.registration.rw.AUCA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import auca.registration.rw.AUCA.model.TeacherModel;
import auca.registration.rw.AUCA.service.TeacherService; // Create this service class

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService service; // Create this service class

    @GetMapping("/teacher")
    public String insertTeacher() {
        return "insertTeacher"; // Create a Thymeleaf template for inserting teachers
    }

    @GetMapping("/listteachers")
    public ModelAndView listTeachers() {
        List<TeacherModel> list = service.listTeachers();
        return new ModelAndView("listteachers", "teachers", list); // Create a Thymeleaf template for listing teachers
    }

    @PostMapping("/saveteacher")
    public String addTeacher(@ModelAttribute TeacherModel teacher) {
        service.save(teacher);
        return "redirect:/listteachers";
    }
}
