package auca.registration.rw.AUCA.service;

import auca.registration.rw.AUCA.model.Semester;
import auca.registration.rw.AUCA.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    public void save(Semester semester) {
        semesterRepository.save(semester);
    }

    public List<Semester> listSemesters() {
        return semesterRepository.findAll();
    }
}
