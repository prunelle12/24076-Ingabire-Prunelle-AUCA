package auca.registration.rw.AUCA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.registration.rw.AUCA.model.AcademicUnit;
import auca.registration.rw.AUCA.repository.AcademicRepository; // Create this repository interface

@Service
public class AcademicService {

    @Autowired
    private AcademicRepository repository; // Create this repository interface

    public void save(AcademicUnit academicUnit) {
        repository.save(academicUnit);
    }

    public List<AcademicUnit> listAcademicUnits() {
        return repository.findAll();
    }
}
