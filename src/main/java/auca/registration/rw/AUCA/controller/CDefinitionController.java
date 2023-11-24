package auca.registration.rw.AUCA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import auca.registration.rw.AUCA.model.CDefinitionModel; // Change the import to the correct model
import auca.registration.rw.AUCA.service.CDefinitionService; // Change the import to the correct service

import java.util.List;

@Controller
public class CDefinitionController {

    @Autowired
    private CDefinitionService service; // Change the service type to the correct service class

    @GetMapping("/cdefinition")
    public String insertCDefinition() {
        return "insertCDefinition"; // Create a Thymeleaf template for inserting CDefinitions
    }

    @GetMapping("/listcdefinitions")
    public ModelAndView listCDefinitions() {
        List<CDefinitionModel> list = service.listCDefinitions();
        return new ModelAndView("listcdefinitions", "cdefinitions", list); // Create a Thymeleaf template for listing CDefinitions
    }

    @PostMapping("/savecdefinition")
    public String addCDefinition(@ModelAttribute CDefinitionModel cDefinition) {
        service.save(cDefinition);
        return "redirect:/listcdefinitions";
    }
}
