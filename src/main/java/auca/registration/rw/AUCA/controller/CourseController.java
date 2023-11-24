package auca.registration.rw.AUCA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

import auca.registration.rw.AUCA.model.CDefinitionModel;
import auca.registration.rw.AUCA.model.CourseModel;
import auca.registration.rw.AUCA.model.RegistrationModel;
import auca.registration.rw.AUCA.model.Student;
import auca.registration.rw.AUCA.model.TeacherModel;
import auca.registration.rw.AUCA.repository.AcademicRepository;
import auca.registration.rw.AUCA.repository.CDefinitionRepository;
import auca.registration.rw.AUCA.repository.CourseRepository;
import auca.registration.rw.AUCA.repository.SemesterRepository;
import auca.registration.rw.AUCA.repository.StudentRepository;
import auca.registration.rw.AUCA.repository.TeacherRepository;
import auca.registration.rw.AUCA.service.CourseService; // Make sure to create this service class
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService service; // Make sure to create this service class

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private AcademicRepository academicUnitRepository;

    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private CDefinitionRepository cdefinitionRepository;
    
    @GetMapping("/courseForm")
    public String showCourseForm(Model model) {
        model.addAttribute("courseModel", new CourseModel());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("semesters", semesterRepository.findAll());
        model.addAttribute("academicUnits", academicUnitRepository.findAll());
        model.addAttribute("teachers", teacherRepository.findAll());
        model.addAttribute("cdefinitions", cdefinitionRepository.findAll());
        return "insertCourse"; // Assuming "courseForm" is the name of your Thymeleaf template
    }
    @GetMapping("/course")
    public String insertCourse() {
        return "insertCourse"; // Create a Thymeleaf template for inserting courses
    }

    @GetMapping("/listcourses")
    public ModelAndView listCourses() {
        List<CourseModel> list = service.listCourses();
        return new ModelAndView("listcourses", "courses", list); // Create a Thymeleaf template for listing courses
    }

    @PostMapping("/savecourse")
    public String addCourse(@ModelAttribute CourseModel course) {
        service.save(course);
        return "redirect:/listcourses";
    }

    @GetMapping("/listCoursesByDepartmentAndSemesterForm")
    public String getCoursesByDepartmentAndSemesterForm(Model model) {
        model.addAttribute("courseModel", new CourseModel());
        model.addAttribute("semesters", semesterRepository.findAll());
        model.addAttribute("academicUnits", academicUnitRepository.findAll());
        return "Question4";
    }


    @PostMapping("/listCoursesByDepartmentAndSemester")
    public String showCoursesByDepartmentAndSemester(@ModelAttribute CourseModel courseModel, Model model) {
        String selectedSemesterId = courseModel.getSemester();
        String selectedDepartmentId = courseModel.getAcademic_unit_id(); // Assuming you have a department_id field in RegistrationModel

        // Use the selected semesterId and departmentId to fetch data from the repository
        List<CourseModel> courses = courseRepository.findBySemesterAndAcademicUnitCode(selectedSemesterId, selectedDepartmentId );


        // Create a list of DTOs (Data Transfer Objects) to hold combined information
        List<CDefinitionModel> cdefinitions = new ArrayList<>();
        List<TeacherModel> teachers = new ArrayList<>();
        // Iterate through registrations and extract relevant information
        for (CourseModel course : courses) {
            CDefinitionModel courseDefinition = course.getCourseDefinition();
            cdefinitions.add(new CDefinitionModel(course.getId(), courseDefinition.getCourse_code(), courseDefinition.getName(), courseDefinition.getDescription()));
            
            TeacherModel teacher = course.getTeacher();
            teachers.add(new TeacherModel(course.getId(), teacher.getName(), teacher.getTutor(), teacher.getAssistant_tutor(), teacher.getQualification()));
        }

        // Add the list of DTOs to the model
        model.addAttribute("cdefinitions", cdefinitions);
        model.addAttribute("teachers", teachers);
        return "Question4";
    }
    
    @GetMapping("/listCoursesByStudentForm")
    public String getCoursesByStudentForm(Model model) {
        model.addAttribute("courseModel", new CourseModel());
        model.addAttribute("students", studentRepository.findAll());

        return "Question5";
    }


    @PostMapping("/listCoursesByStudent")
    public String showCoursesByStudent(@ModelAttribute CourseModel courseModel, Model model) {
        String selectedStudentId = courseModel.getStudent_id();


        List<CourseModel> courses = courseRepository.findByStudentRegNo(selectedStudentId );


        // Create a list of DTOs (Data Transfer Objects) to hold combined information
        List<CDefinitionModel> cdefinitions = new ArrayList<>();
        List<TeacherModel> teachers = new ArrayList<>();
        // Iterate through registrations and extract relevant information
        for (CourseModel course : courses) {
            CDefinitionModel courseDefinition = course.getCourseDefinition();
            cdefinitions.add(new CDefinitionModel(course.getId(), courseDefinition.getCourse_code(), courseDefinition.getName(), courseDefinition.getDescription()));
            
            TeacherModel teacher = course.getTeacher();
            teachers.add(new TeacherModel(course.getId(), teacher.getName(), teacher.getTutor(), teacher.getAssistant_tutor(), teacher.getQualification()));
        }

        // Add the list of DTOs to the model
        model.addAttribute("cdefinitions", cdefinitions);
        model.addAttribute("teachers", teachers);
        return "Question5";
    }
}
