package auca.registration.rw.AUCA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.registration.rw.AUCA.model.AcademicUnit;
import auca.registration.rw.AUCA.model.RegistrationModel;
import auca.registration.rw.AUCA.model.Semester;
import auca.registration.rw.AUCA.repository.AcademicRepository; // Create this repository interface
import auca.registration.rw.AUCA.repository.RegistrationRepository;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository repository; // Create this repository interface

    public void save(RegistrationModel registration) {
        repository.save(registration);
    }

    public List<RegistrationModel> listRegistrations() {
        return repository.findAll();
    }

    public List<RegistrationModel> listStudentsBySemester(String semester_id) {
        return repository.findBySemester_id(semester_id);
    }

    public List<RegistrationModel> listStudentsBySemesterAndDepartment(String semester_id, String academic_unit_id) {
        return repository.findBySemesterIdAndAcademicUnitCode(semester_id, academic_unit_id);
    }

    public List<RegistrationModel> listStudentsBySemesterAndCourse(String semester_id, String course_id) {
        return repository.findBySemesterIdAndCourseId(semester_id, course_id);
    }
    
	public List<Semester> listSemesters() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
