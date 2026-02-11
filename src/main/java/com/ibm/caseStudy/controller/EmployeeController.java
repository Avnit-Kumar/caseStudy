package com.ibm.caseStudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ibm.caseStudy.Service.EmployeeService;
import com.ibm.caseStudy.exception.EmployeeAlreadyExistsException;
import com.ibm.caseStudy.model.Employee;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(
            @ModelAttribute("employee") Employee employee,
            Model model) {

        // Validate required fields
        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
            model.addAttribute("error", "First name is required");
            return "add-employee";
        }

        if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
            model.addAttribute("error", "Last name is required");
            return "add-employee";
        }

        if (employee.getPosition() == null || employee.getPosition().trim().isEmpty()) {
            model.addAttribute("error", "Position is required");
            return "add-employee";
        }

        if (employee.getBirthDate() == null) {
            model.addAttribute("error", "Birth date is required");
            return "add-employee";
        }

        if (employee.getBirthDate().isAfter(LocalDate.now())) {
            model.addAttribute("error", "Birth date cannot be in the future");
            return "add-employee";
        }

        try {
            // Service will generate UID and save employee
            service.addEmployee(employee);
            model.addAttribute("success", "Employee added successfully! UID: " + employee.getUid());
        } catch (EmployeeAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred. Please try again.");
        }

        return "add-employee";
    }
}
