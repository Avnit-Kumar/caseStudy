
package com.ibm.caseStudy.controller;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
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

import com.ibm.caseStudy.dto.CompensationDTO;
import com.ibm.caseStudy.dto.MonthlyCompensationDTO;
import com.ibm.caseStudy.model.Compensation;
import com.ibm.caseStudy.model.CompensationType;
import com.ibm.caseStudy.service.CompensationService;

@Controller
@RequestMapping("/compensation")
public class CompensationController {

    private final CompensationService service;

    public CompensationController(CompensationService service) {
        this.service = service;
    }

    @GetMapping("/add/{employeeId}")
    public String showForm(@PathVariable Long employeeId, Model model) {

        CompensationDTO dto = new CompensationDTO();
        dto.setEmployeeId(employeeId);

        model.addAttribute("compensation", dto);
        model.addAttribute("types", CompensationType.values());

        return "add-compensation";
    }

    @PostMapping("/add")
    public String addCompensation(
            @Valid @ModelAttribute("compensation") CompensationDTO dto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("types", CompensationType.values());
            return "add-compensation";
        }

        try {
            service.addCompensation(dto);
            model.addAttribute("success", "Compensation added successfully!");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("types", CompensationType.values());
        return "add-compensation";
    }
    
    @GetMapping("/history/{employeeId}")
    public String showHistoryForm(@PathVariable Long employeeId, Model model) {

        model.addAttribute("employeeId", employeeId);
        return "compensation-history-form";
    }

    @PostMapping("/history")
    public String viewHistory(
            @RequestParam Long employeeId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            Model model) {

        LocalDate start = LocalDate.parse(startDate + "-01");
        LocalDate end = LocalDate.parse(endDate + "-01")
                .withDayOfMonth(
                        LocalDate.parse(endDate + "-01").lengthOfMonth());

        if (end.isBefore(start)) {
            model.addAttribute("error", "End date cannot be before start date");
            model.addAttribute("employeeId", employeeId);
            return "compensation-history-form";
        }

        List<MonthlyCompensationDTO> history =
                service.getMonthlyCompensationHistory(employeeId, start, end);

        model.addAttribute("history", history);
        model.addAttribute("employeeId", employeeId);

        return "compensation-history-result";
    }
    
    @GetMapping("/breakdown/{employeeId}/{month}")
    public String viewBreakdown(
            @PathVariable Long employeeId,
            @PathVariable String month,
            Model model) {

        YearMonth yearMonth = YearMonth.parse(month);

        List<CompensationDTO> breakdown =
                service.getCompensationBreakdown(employeeId, yearMonth);

        BigDecimal total = breakdown.stream()
                .map(CompensationDTO::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("breakdown", breakdown);
        model.addAttribute("total", total);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("selectedMonth", month);

        return "compensation-breakdown";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditCompensationForm(
            @PathVariable Long id,
            Model model) {

        Compensation comp = service.getCompensationEntityById(id);

        model.addAttribute("comp", comp);

        return "edit-compensation";
    }
    
    @PostMapping("/edit/{id}")
    public String updateCompensation(
            @PathVariable Long id,
            @RequestParam BigDecimal amount,
            @RequestParam String description,
            Model model) {

        try {
            service.updateCompensation(id, amount, description);

            Compensation comp = service.getCompensationEntityById(id);

            return "redirect:/compensation/breakdown/"
                    + comp.getEmployee().getId()
                    + "/"
                    + java.time.YearMonth.from(comp.getPaymentDate());

        } catch (RuntimeException e) {

            Compensation comp = service.getCompensationEntityById(id);

            model.addAttribute("comp", comp);
            model.addAttribute("error", e.getMessage());

            return "edit-compensation";
        }
    }




}
