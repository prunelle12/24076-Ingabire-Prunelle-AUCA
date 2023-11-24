package auca.registration.rw.AUCA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.registration.rw.AUCA.model.TeacherModel;
import auca.registration.rw.AUCA.repository.TeacherRepository;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    public void save(TeacherModel teacher) {
        repository.save(teacher);
    }

    public List<TeacherModel> listTeachers() {
        return repository.findAll();
    }
}
