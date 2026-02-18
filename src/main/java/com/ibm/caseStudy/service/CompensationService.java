package com.ibm.caseStudy.service;

import java.time.LocalDate;
import java.util.List;

import com.ibm.caseStudy.dto.CompensationDTO;
import com.ibm.caseStudy.dto.MonthlyCompensationDTO;

public interface CompensationService {

	void addCompensation(CompensationDTO dto);

	List<MonthlyCompensationDTO> getMonthlyCompensationHistory(Long employeeId, LocalDate startDate, LocalDate endDate);

	List<CompensationDTO> getCompensationBreakdown(Long employeeId, java.time.YearMonth month);

	void updateCompensation(Long id, java.math.BigDecimal amount, String description);

	com.ibm.caseStudy.model.Compensation getCompensationEntityById(Long id);

}