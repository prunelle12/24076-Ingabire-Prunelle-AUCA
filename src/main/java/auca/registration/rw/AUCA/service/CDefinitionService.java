package auca.registration.rw.AUCA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import auca.registration.rw.AUCA.model.CDefinitionModel;
import auca.registration.rw.AUCA.repository.CDefinitionRepository; // Create this repository interface

import java.util.List;

@Service
public class CDefinitionService {

    @Autowired
    private CDefinitionRepository repository; // Create this repository interface

    public void save(CDefinitionModel cDefinition) {
        repository.save(cDefinition);
    }

    public List<CDefinitionModel> listCDefinitions() {
        return repository.findAll();
    }
}
