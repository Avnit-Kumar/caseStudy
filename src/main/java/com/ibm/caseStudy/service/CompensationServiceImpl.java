package com.ibm.caseStudy.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.caseStudy.dto.CompensationDTO;
import com.ibm.caseStudy.exception.BusinessException;
import com.ibm.caseStudy.mapper.CompensationMapper;
import com.ibm.caseStudy.model.Compensation;
import com.ibm.caseStudy.model.Employee;
import com.ibm.caseStudy.repository.CompensationRepository;
import com.ibm.caseStudy.repository.EmployeeRepository;

public class CompensationServiceImpl implements CompensationService {
	@Autowired
	private CompensationRepository compensationRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	private boolean isBlank(String s) {
		return s == null || s.trim().isEmpty();
	}

	@Override
	public Compensation addCompensation(CompensationDTO dto) {
		String type = dto.getType();
		BigDecimal amount = dto.getAmount();

		Employee employee = employeeRepository.findById(dto.getEmployeeId())
				.orElseThrow(() -> new BusinessException("Employee not found."));
		int year = dto.getDate().getYear();
		int month = dto.getDate().getMonthValue();

		if ("Salary".equals(type)) {
            boolean exists = compensationRepository.existsSalaryForMonth(
                    dto.getEmployeeId(), year, month);
            if (exists) {
                throw new BusinessException("Only one salary entry allowed per employee per month.");
            }
		}

		List<String> positiveTypes = Arrays.asList("Bonus", "Commission", "Allowance");
		if (positiveTypes.contains(type)) {
			if (amount.compareTo(BigDecimal.ZERO) <= 0) {
				throw new BusinessException(type + " amount must be greater than 0.");
			}
			if (isBlank(dto.getDescription())) {
				throw new BusinessException(type + " requires a description.");
			}
		}

		if ("Adjustment".equals(type)) {
			if (amount.compareTo(BigDecimal.ZERO) == 0) {
				throw new BusinessException("Adjustment amount cannot be zero.");
			}
			if (isBlank(dto.getDescription())) {
				throw new BusinessException("Adjustment requires a description.");
			}
		}

		Compensation entity = CompensationMapper.toEntity(dto, employee);
		return compensationRepository.save(entity);
	}

	@Override
	public Map<String, Double> getMonthlyTotalsForEmployee(Long employeeId, LocalDate start, LocalDate end) {
        List<Object[]> rows = compensationRepository
                .getMonthlyTotalsForEmployee(employeeId, start, end);
        Map<String, Double> result = new LinkedHashMap<>();
        for (Object[] row : rows) {
            int year = ((Number) row[0]).intValue();
            int month = ((Number) row[1]).intValue();
            double total = ((Number) row[2]).doubleValue();
            String key = year + "-" + (month < 10 ? "0" + month : month);
            result.put(key, total);
        }
        return result;
	}

	@Override
	public List<Compensation> getBreakdown(Long employeeId, int year, int month) {
		return compensationRepository.getByEmployeeAndMonth(employeeId, year, month);
	}

	@Override
	public Compensation updateCompensation(Long id, Double newAmount, String description) {
		Compensation comp = compensationRepository.findById(id)
				.orElseThrow(() -> new BusinessException("Compensation not found."));
		String type = comp.getType();
		if (!"Salary".equals(type)) {
			List<String> positiveTypes = Arrays.asList("Bonus", "Commission", "Allowance");
			if (positiveTypes.contains(type)) {
				if (newAmount <= 0) {
					throw new BusinessException(type + " amount must be greater than 0.");
				}
				if (isBlank(description)) {
					throw new BusinessException(type + " requires a description.");
				}
			}
			if ("Adjustment".equals(type)) {
				if (newAmount == 0) {
					throw new BusinessException("Adjustment amount cannot be zero.");
				}
				if (isBlank(description)) {
					throw new BusinessException("Adjustment requires a description.");
				}
			}
		}
		comp.setAmount(BigDecimal.valueOf(newAmount));
		comp.setDescription(description);
		return compensationRepository.save(comp);
	}
}
