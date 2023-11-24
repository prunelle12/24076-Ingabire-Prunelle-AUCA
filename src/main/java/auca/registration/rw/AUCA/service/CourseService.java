package auca.registration.rw.AUCA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import auca.registration.rw.AUCA.model.CourseModel;
import auca.registration.rw.AUCA.model.RegistrationModel;
import auca.registration.rw.AUCA.repository.CourseRepository; // Create this repository interface

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository; // Create this repository interface

    public void save(CourseModel course) {
        repository.save(course);
    }
    public List<CourseModel> listCoursesBySemesterAndDepartment(String semester, String academic_unit_id) {
        return repository.findBySemesterAndAcademicUnitCode(semester, academic_unit_id);
    }
    
    public List<CourseModel> listCoursesByStudent(String student_id) {
        return repository.findByStudentRegNo(student_id);
    }
    public List<CourseModel> listCourses() {
        return repository.findAll();
    }
}
