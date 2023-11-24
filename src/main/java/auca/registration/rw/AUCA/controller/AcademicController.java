package auca.registration.rw.AUCA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import auca.registration.rw.AUCA.model.AcademicUnit;
import auca.registration.rw.AUCA.model.StudentCourseModel;
import auca.registration.rw.AUCA.repository.AcademicRepository;
import auca.registration.rw.AUCA.repository.CourseRepository;
import auca.registration.rw.AUCA.service.AcademicService; // Make sure to create this service class

import java.util.List;

@Controller
public class AcademicController {

    @Autowired
    private AcademicService service; // Make sure to create this service class

    @Autowired
    private AcademicRepository academicRepository;
    
    @GetMapping("/academicForm")
    public String showAcademicForm(Model model) {
        model.addAttribute("academicModel", new AcademicUnit());
        model.addAttribute("academics", academicRepository.findAll());


        return "insertAcademicUnit"; // Assuming "courseForm" is the name of your Thymeleaf template
    }
    @GetMapping("/academicunit")
    public String insertAcademicUnit() {
        return "insertAcademicUnit"; // Create a Thymeleaf template for inserting academic units
    }

    @GetMapping("/listacademicunits")
    public ModelAndView listAcademicUnits() {
        List<AcademicUnit> list = service.listAcademicUnits();
        return new ModelAndView("listacademicunits", "academicunits", list); // Create a Thymeleaf template for listing academic units
    }

    @PostMapping("/saveacademicunit")
    public String addAcademicUnit(@ModelAttribute AcademicUnit academicUnit) {
        service.save(academicUnit);
        return "redirect:/listacademicunits";
    }
}
