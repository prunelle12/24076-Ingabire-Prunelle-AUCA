package auca.registration.rw.AUCA.controller;

import auca.registration.rw.AUCA.model.Semester;
import auca.registration.rw.AUCA.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

   

    @GetMapping("/semester")
    public String insertSemester() {
        return "insertSemester"; // Update the view name to match your actual view
    }

    @GetMapping("/listsemesters")
    public ModelAndView listSemesters() {
        List<Semester> semesterList = semesterService.listSemesters();
        return new ModelAndView("listsemesters", "semesters", semesterList); // Update the view name to match your actual view
    }

    @PostMapping("/saveSemester")
    public String addSemester(@ModelAttribute Semester semester) {
        semesterService.save(semester);
        return "redirect:/listsemesters"; // Update the redirect URL to match your actual view
    }
}
