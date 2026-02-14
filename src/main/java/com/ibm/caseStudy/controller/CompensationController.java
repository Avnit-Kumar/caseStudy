package com.ibm.caseStudy.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibm.caseStudy.dto.CompensationDTO;
import com.ibm.caseStudy.exception.AmountNegativeException;
import com.ibm.caseStudy.exception.AmountZeroException;
import com.ibm.caseStudy.exception.CompensationDoesNotExistException;
import com.ibm.caseStudy.exception.NullDescriptionException;
import com.ibm.caseStudy.exception.SalaryAlreadyExistsException;
import com.ibm.caseStudy.mapper.CompensationMapper;
import com.ibm.caseStudy.model.Compensation;
import com.ibm.caseStudy.service.CompensationService;

@Controller
@RequestMapping("/employees/{employeeId}/compensation")
public class CompensationController {
	@Autowired
	private CompensationService compensationService;

	@GetMapping("/")
	public String showHistory(@PathVariable Long employeeId,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate start,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate end, Model model) {
		if (start == null) start = LocalDate.now().withDayOfYear(1);
	    if (end == null) end = LocalDate.now();
		Map<String, Double> totals = compensationService.getMonthlyTotalsForEmployee(employeeId, start, end);
		model.addAttribute("totals", totals);
		return "compensation-history";
	}

	@GetMapping("/add")
	public String addCompensation(@PathVariable Long employeeId, Model model) {
		CompensationDTO dto = new CompensationDTO();
		dto.setEmployeeId(employeeId);
		model.addAttribute("compensation", dto);
		return "add-compensation";
	}

	@PostMapping("/add")
	public String addCompensation(@PathVariable Long employeeId,
			@Valid @ModelAttribute("compensation") CompensationDTO compensationDTO, BindingResult result,
			RedirectAttributes redirectAttribute) {
		compensationDTO.setEmployeeId(employeeId);

		if (result.hasErrors()) {
			return "add-compensation";
		}
		try {
			compensationService.addCompensation(compensationDTO);
		} catch (SalaryAlreadyExistsException ex) {
			result.reject("global.error", ex.getMessage());
			return "add-compensation";
		} catch (AmountNegativeException | AmountZeroException ex) {
			result.rejectValue("amount", "error.amount", ex.getMessage());
			return "add-compensation";
		} catch (NullDescriptionException ex) {
			result.rejectValue("description", "error.description", ex.getMessage());
			return "add-compensation";
		} catch (Exception ex) {
			result.reject("global.error", "An unexpected error occurred.");
			return "add-compensation";
		}

		redirectAttribute.addFlashAttribute("message", "Compensation added successfully!");
		return "redirect:/employees/" + employeeId + "/compensation";
	}

	@GetMapping("/breakdown/{year}/{month}")
	public String viewBreakDown(@PathVariable Long employeeId, @PathVariable int year, @PathVariable int month,
			Model model) {
		List<Compensation> entities = compensationService.getBreakdown(employeeId, year, month);

		List<CompensationDTO> dtos = entities.stream().map(CompensationMapper::toDTO).collect(Collectors.toList());
		BigDecimal total = compensationService.getSingleMonthlyTotal(employeeId, year, month);

		model.addAttribute("compensations", dtos);
		model.addAttribute("total", total);
		model.addAttribute("selectedMonth", month);
		model.addAttribute("selectedYear", year);
		return "compensation-breakdown";
	}

	@GetMapping("/{id}/edit/{year}/{month}")
	public String showEditForm(@PathVariable Long id, 
	                           @PathVariable Long employeeId,
	                           @PathVariable int year, 
	                           @PathVariable int month, 
	                           Model model, 
	                           RedirectAttributes redirectAttributes) {
	    try {
	        Compensation comp = compensationService.findCompensationById(id);
	        model.addAttribute("compensation", CompensationMapper.toDTO(comp));
	        
	        model.addAttribute("year", year);
	        model.addAttribute("month", month);
	        
	        return "edit-compensation";
	    } catch(CompensationDoesNotExistException ex) {
	        redirectAttributes.addFlashAttribute("error", ex.getMessage());
	        return "redirect:/employees/" + employeeId + "/compensation/breakdown/" + year + "/" + month;
	    }
	}
	
	@PostMapping("/{id}/edit/{year}/{month}")
	public String updateCompensation(@PathVariable Long employeeId,
	                                 @PathVariable Long id,
	                                 @PathVariable int year, 
	                                 @PathVariable int month,
	                                 @ModelAttribute("compensation") CompensationDTO dto,
	                                 BindingResult result,
	                                 RedirectAttributes redirectAttributes) {
	    try {
	        compensationService.updateCompensation(id, dto.getAmount().doubleValue(), dto.getDescription());	        
	    } catch (AmountNegativeException | AmountZeroException ex) {
	        result.rejectValue("amount", "error.amount", ex.getMessage());
	        return "edit-compensation";
	    } catch (NullDescriptionException ex) {
	        result.rejectValue("description", "error.description", ex.getMessage());
	        return "edit-compensation";
	    } catch (Exception ex) {
	        result.reject("global.error", "Update failed: " + ex.getMessage());
	        return "edit-compensation";
	    }

	    redirectAttributes.addFlashAttribute("message", "Updated successfully!");
	    return "redirect:/employees/" + employeeId + "/compensation/breakdown/" + year + "/" + month;
	}
}
