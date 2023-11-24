package auca.registration.rw.AUCA.controller;

import auca.registration.rw.AUCA.model.RegistrationModel;
import auca.registration.rw.AUCA.model.Semester;
import auca.registration.rw.AUCA.model.Student;
import auca.registration.rw.AUCA.repository.AcademicRepository;
import auca.registration.rw.AUCA.repository.CourseRepository;
import auca.registration.rw.AUCA.repository.RegistrationRepository;
import auca.registration.rw.AUCA.repository.SemesterRepository;
import auca.registration.rw.AUCA.repository.StudentRepository;
import auca.registration.rw.AUCA.service.RegistrationService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService; // Make sure to create this service class

    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private AcademicRepository academicUnitRepository;

    @GetMapping("/registrationForm")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationModel", new RegistrationModel());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("semesters", semesterRepository.findAll());
        model.addAttribute("academicUnits", academicUnitRepository.findAll());
        return "showRegistrationForm";
    }
    @GetMapping("/registration")
    public String insertRegistration() {
        return "insertRegistration"; // Create a Thymeleaf template for the registration form
    }



    @GetMapping("/listregistrations")
    public ModelAndView listRegistrations() {
        List<RegistrationModel> registrationList = registrationService.listRegistrations();
        return new ModelAndView("listregistrations", "registrations", registrationList);
        // Create a Thymeleaf template for listing registrations
    }
    @PostMapping("/saveregistration")
    public String saveRegistration(@ModelAttribute RegistrationModel registration) {
        registrationService.save(registration);
        return "redirect:/listregistrations";
    }

    @GetMapping("/listStudentBySemesterForm")
    public String getStudentForm(Model model) {
        model.addAttribute("registrationModel", new RegistrationModel());
        model.addAttribute("semesters", semesterRepository.findAll());
        return "Question1";
    }


    @PostMapping("/listStudentsBySemester")
    public String showStudentsBySemester(@ModelAttribute RegistrationModel registrationModel, Model model) {
        String selectedSemesterId = registrationModel.getSemester_id();
        
        // Use the selected semesterId to fetch data from the repository
        List<RegistrationModel> registrations = registrationRepository.findBySemester_id(selectedSemesterId);

        // Create a list of DTOs (Data Transfer Objects) to hold combined information
        List<Student> students = new ArrayList<>();

        // Iterate through registrations and extract relevant information
        for (RegistrationModel registration : registrations) {
            Student student = registration.getStudent();
            students.add(new Student(registration.getStudent_id(), student.getFirstName(), student.getDateOfBirth()));
        }

        // Add the list of DTOs to the model
        model.addAttribute("students", students);

        return "Question1";
    }


    @GetMapping("/listStudentBySemesterAndDepartmentForm")
    public String getStudentsForm(Model model) {
        model.addAttribute("registrationModel", new RegistrationModel());
        model.addAttribute("semesters", semesterRepository.findAll());
        model.addAttribute("academicUnits", academicUnitRepository.findAll());
        return "Question2";
    }


    @PostMapping("/listStudentsBySemesterAndDepartment")
    public String showStudentBySemester(@ModelAttribute RegistrationModel registrationModel, Model model) {
        String selectedSemesterId = registrationModel.getSemester_id();
        String selectedDepartmentId = registrationModel.getAcademic_unit_id(); // Assuming you have a department_id field in RegistrationModel

        // Use the selected semesterId and departmentId to fetch data from the repository
        List<RegistrationModel> registrations = registrationRepository.findBySemesterIdAndAcademicUnitCode(selectedSemesterId, selectedDepartmentId );


        // Create a list of DTOs (Data Transfer Objects) to hold combined information
        List<Student> students = new ArrayList<>();

        // Iterate through registrations and extract relevant information
        for (RegistrationModel registration : registrations) {
            Student student = registration.getStudent();
            students.add(new Student(registration.getStudent_id(), student.getFirstName(), student.getDateOfBirth()));
        }

        // Add the list of DTOs to the model
        model.addAttribute("students", students);

        return "Question2";
    }
    
    @GetMapping("/listStudentByCourseAndSemesterForm")
    public String getStudentsByCourseAndSemesterForm(Model model) {
        model.addAttribute("registrationModel", new RegistrationModel());
        model.addAttribute("semesters", semesterRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        return "Question3";
    }


    @PostMapping("/listStudentsByCourseAndSemester")
    public String showStudentByCourseAndSemester(@ModelAttribute RegistrationModel registrationModel, Model model) {
        String selectedSemesterId = registrationModel.getSemester_id();
        String selectedCourseId = registrationModel.getCourse_id(); // Assuming you have a department_id field in RegistrationModel

        // Use the selected semesterId and departmentId to fetch data from the repository
        List<RegistrationModel> registrations = registrationRepository.findBySemesterIdAndCourseId(selectedSemesterId, selectedCourseId );


        // Create a list of DTOs (Data Transfer Objects) to hold combined information
        List<Student> students = new ArrayList<>();

        // Iterate through registrations and extract relevant information
        for (RegistrationModel registration : registrations) {
            Student student = registration.getStudent();
            students.add(new Student(registration.getStudent_id(), student.getFirstName(), student.getDateOfBirth()));
        }

        // Add the list of DTOs to the model
        model.addAttribute("students", students);

        return "Question3";
    }
  
}
