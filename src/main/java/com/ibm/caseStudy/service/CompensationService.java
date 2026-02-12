package com.ibm.caseStudy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.ibm.caseStudy.dto.CompensationDTO;
import com.ibm.caseStudy.model.Compensation;

public interface CompensationService {
	Compensation addCompensation(CompensationDTO dto);
    Map<String, Double> getMonthlyTotalsForEmployee(Long employeeId, LocalDate start, LocalDate end);
    List<Compensation> getBreakdown(Long employeeId, int year, int month);
    Compensation updateCompensation(Long id, Double amount, String description);
}
