package com.ibm.caseStudy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.caseStudy.service.EmployeeService;
import com.ibm.caseStudy.dto.EmployeeDTO;
import com.ibm.caseStudy.exception.EmployeeAlreadyExistsException;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    
    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        return "add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(
            @Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "add-employee";
        }

        try {
            service.addEmployee(employeeDTO);
            model.addAttribute("success", "Employee added successfully!");
            model.addAttribute("employee", new EmployeeDTO());
        } catch (EmployeeAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "add-employee";
    }


    @GetMapping("/search")
    public String showSearchPage() {
        return "search-employee";
    }

    @GetMapping("/results")
    public String searchEmployees(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String position,
            Model model) {
    	
    	model.addAttribute("searched",true);
    	
    	if    ((firstName==null || firstName.trim().isEmpty())&&
    			(lastName==null || lastName.trim().isEmpty())&&
    			(position ==null|| position.trim().isEmpty())) {
    		model.addAttribute("message","please enter at least one search criteria");
    		return "search-employee";
    	}

        List<EmployeeDTO> results =
                service.searchEmployees(firstName, lastName, position);

        model.addAttribute("employees", results);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("position", position);

        return "search-employee";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        EmployeeDTO employeeDTO = service.getEmployeeById(id);
        model.addAttribute("employee", employeeDTO);

        return "edit-employee";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(
            @PathVariable Long id,
            @Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
            BindingResult result,
            Model model) {
    	
    	System.out.println(employeeDTO.getId());

        if (result.hasErrors()) {
            return "edit-employee";
        }
      
        employeeDTO.setId(id);

        try {
            service.updateEmployee(employeeDTO);
            model.addAttribute("success", "Employee updated successfully!");
        } catch (EmployeeAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "edit-employee";
    }
}

