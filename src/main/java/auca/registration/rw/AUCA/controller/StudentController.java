package auca.registration.rw.AUCA.controller;

//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import auca.registration.rw.AUCA.model.Student;
import auca.registration.rw.AUCA.service.StudentService;
import java.util.*;
@Controller
public class StudentController {

	@Autowired
	private StudentService service;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/student")
	public String insertStudent() {
		return "insertStudent";
	}
	
	@GetMapping("/liststudent")
	public ModelAndView listStudent() {
		List<Student>list=service.listStudent();
	
		
		return new ModelAndView("liststudent", "student", list);
	}
	
	@PostMapping("/save")
	public String addStudent(@ModelAttribute Student s) {
		service.save(s);
		return "redirect:/liststudent";
	}
}
