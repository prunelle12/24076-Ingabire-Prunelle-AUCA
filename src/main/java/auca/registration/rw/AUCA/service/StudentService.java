package auca.registration.rw.AUCA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.registration.rw.AUCA.model.Student;
import auca.registration.rw.AUCA.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository bRepo;
	
	public void save(Student s) {
		bRepo.save(s);
	}
	public List<Student> listStudent(){
		return bRepo.findAll();
	}
}
