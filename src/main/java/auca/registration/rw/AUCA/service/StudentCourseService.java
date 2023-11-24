package auca.registration.rw.AUCA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.registration.rw.AUCA.model.AcademicUnit;
import auca.registration.rw.AUCA.model.RegistrationModel;
import auca.registration.rw.AUCA.model.StudentCourseModel;
import auca.registration.rw.AUCA.repository.AcademicRepository; // Create this repository interface
import auca.registration.rw.AUCA.repository.RegistrationRepository;
import auca.registration.rw.AUCA.repository.StudentCourseRepository;

@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseRepository repository; // Create this repository interface

    public void save(StudentCourseModel studentcourse) {
        repository.save(studentcourse);
    }

    public List<StudentCourseModel> listStudentCourses() {
        return repository.findAll();
    }
}
