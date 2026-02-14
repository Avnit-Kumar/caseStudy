package com.ibm.caseStudy.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ibm.caseStudy.dto.CompensationDTO;
import com.ibm.caseStudy.model.Compensation;

public interface CompensationService {
	Compensation findCompensationById(Long id);
	Compensation addCompensation(CompensationDTO dto);
    Map<String, Double> getMonthlyTotalsForEmployee(Long employeeId, LocalDate start, LocalDate end);
    BigDecimal getSingleMonthlyTotal(Long empId, int year, int month);
    List<Compensation> getBreakdown(Long employeeId, int year, int month);
    Compensation updateCompensation(Long id, Double amount, String description);
}
