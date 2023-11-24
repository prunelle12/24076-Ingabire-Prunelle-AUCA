package auca.registration.rw.AUCA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.registration.rw.AUCA.model.AcademicUnit;
import auca.registration.rw.AUCA.model.CDefinitionModel;



public interface CDefinitionRepository extends JpaRepository<CDefinitionModel, String>{

}
