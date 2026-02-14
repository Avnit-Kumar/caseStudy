package com.ibm.caseStudy.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.caseStudy.dto.CompensationDTO;
import com.ibm.caseStudy.exception.AmountNegativeException;
import com.ibm.caseStudy.exception.AmountZeroException;
import com.ibm.caseStudy.exception.CompensationDoesNotExistException;
import com.ibm.caseStudy.exception.NullDescriptionException;
import com.ibm.caseStudy.exception.SalaryAlreadyExistsException;
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
				.orElseThrow(() -> new RuntimeException("Employee not found."));
		int year = dto.getDate().getYear();
		int month = dto.getDate().getMonthValue();

		if ("Salary".equals(type)) {
            boolean exists = compensationRepository.existsSalaryForMonth(
                    dto.getEmployeeId(), year, month);
            if (exists) {
                throw new SalaryAlreadyExistsException("Salary Already Exists for this month");
            }
		}

		List<String> positiveTypes = Arrays.asList("Bonus", "Commission", "Allowance");
		if (positiveTypes.contains(type)) {
			if (amount.compareTo(BigDecimal.ZERO) <= 0) {
				throw new AmountNegativeException(type + " amount must be greater than 0.");
			}
			if (isBlank(dto.getDescription())) {
				throw new NullDescriptionException(type + " requires a description.");
			}
		}

		if ("Adjustment".equals(type)) {
			if (amount.compareTo(BigDecimal.ZERO) == 0) {
				throw new AmountZeroException("Adjustment amount cannot be zero.");
			}
			if (isBlank(dto.getDescription())) {
				throw new NullDescriptionException("Adjustment requires a description.");
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
	public BigDecimal getSingleMonthlyTotal(Long empId, int year, int month) {
		BigDecimal total = compensationRepository.getSingleMonthlyTotal(empId, year, month);
		return total != null ? total : BigDecimal.ZERO;
	}

	@Override
	public List<Compensation> getBreakdown(Long employeeId, int year, int month) {
		return compensationRepository.getByEmployeeAndMonth(employeeId, year, month);
	}

	@Override
	public Compensation updateCompensation(Long id, Double newAmount, String description) {
		Compensation comp = compensationRepository.findById(id)
				.orElseThrow(() -> new CompensationDoesNotExistException("Compensation not found."));
		String type = comp.getType();
		if (!"Salary".equals(type)) {
			List<String> positiveTypes = Arrays.asList("Bonus", "Commission", "Allowance");
			if (positiveTypes.contains(type)) {
				if (newAmount <= 0) {
					throw new AmountNegativeException(type + " amount must be greater than 0.");
				}
				if (isBlank(description)) {
					throw new NullDescriptionException(type + " requires a description.");
				}
			}
			if ("Adjustment".equals(type)) {
				if (newAmount == 0) {
					throw new AmountZeroException("Adjustment amount cannot be zero.");
				}
				if (isBlank(description)) {
					throw new NullDescriptionException("Adjustment requires a description.");
				}
			}
		}
		comp.setAmount(BigDecimal.valueOf(newAmount));
		comp.setDescription(description);
		return compensationRepository.save(comp);
	}

	@Override
	public Compensation findCompensationById(Long id) {
		Compensation comp = compensationRepository.findById(id)
				.orElseThrow(() -> new CompensationDoesNotExistException("Compensation not found."));
		return comp;
	}
}
