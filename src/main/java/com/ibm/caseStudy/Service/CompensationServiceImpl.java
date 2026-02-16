package com.ibm.caseStudy.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.caseStudy.dto.CompensationDTO;
import com.ibm.caseStudy.dto.MonthlyCompensationDTO;
import com.ibm.caseStudy.model.Compensation;
import com.ibm.caseStudy.model.CompensationType;
import com.ibm.caseStudy.model.Employee;
import com.ibm.caseStudy.repository.CompensationRepository;
import com.ibm.caseStudy.repository.EmployeeRepository;


@Service
@Transactional
public class CompensationServiceImpl implements CompensationService {

    private final CompensationRepository compensationRepo;
    private final EmployeeRepository employeeRepo;

    public CompensationServiceImpl(CompensationRepository compensationRepo,
                                   EmployeeRepository employeeRepo) {
        this.compensationRepo = compensationRepo;
        this.employeeRepo = employeeRepo;
    }
    
    @Override
    public List<MonthlyCompensationDTO> getMonthlyCompensationHistory(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate) {

        List<Compensation> compensations =
                compensationRepo.findByEmployeeIdAndPaymentDateBetween(
                        employeeId, startDate, endDate);

        Map<YearMonth, BigDecimal> monthlyTotals = new TreeMap<>();

        for (Compensation comp : compensations) {

            YearMonth ym = YearMonth.from(comp.getPaymentDate());

            monthlyTotals.putIfAbsent(ym, BigDecimal.ZERO);

            monthlyTotals.put(ym,
                    monthlyTotals.get(ym).add(comp.getAmount()));
        }

        return monthlyTotals.entrySet()
                .stream()
                .map(e -> new MonthlyCompensationDTO(
                        e.getKey(),
                        e.getValue()))
                .collect(Collectors.toList());
    }
    @Override
    public List<CompensationDTO> getCompensationBreakdown(
            Long employeeId,
            YearMonth month) {

        LocalDate endOfMonth = month.atEndOfMonth();

        List<Compensation> compensations =
                compensationRepo.findByEmployeeIdAndPaymentDate(
                        employeeId, endOfMonth);

        return compensations.stream().map(comp -> {

            CompensationDTO dto = new CompensationDTO();
            dto.setId(comp.getId());
            dto.setType(comp.getType());
            dto.setAmount(comp.getAmount());
            dto.setDescription(comp.getDescription());
            dto.setPaymentDate(comp.getPaymentDate());
            dto.setEmployeeId(employeeId);

            return dto;

        }).collect(Collectors.toList());
    }
    
    @Override
    public void updateCompensation(Long id, BigDecimal amount, String description) {

        Compensation comp = compensationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Compensation not found"));

        // Validation rules same as Story 1.5
        switch (comp.getType()) {

            case BONUS:
            case COMMISSION:
            case ALLOWANCE:
                if (amount.compareTo(BigDecimal.ZERO) <= 0)
                    throw new RuntimeException("Amount must be greater than zero");
                if (description == null || description.trim().isEmpty())
                    throw new RuntimeException("Description is required");
                break;

            case ADJUSTMENT:
                if (amount.compareTo(BigDecimal.ZERO) == 0)
                    throw new RuntimeException("Amount cannot be zero");
                if (description == null || description.trim().isEmpty())
                    throw new RuntimeException("Description is required");
                break;

            case SALARY:
                // Salary can be zero or negative
                // Description optional
                break;
        }

        comp.setAmount(amount);
        comp.setDescription(description);

        compensationRepo.save(comp);
    }
    
    @Override
    public Compensation getCompensationEntityById(Long id) {
        return compensationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Compensation not found"));
    }





    @Override
    public void addCompensation(CompensationDTO dto) {

        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LocalDate endOfMonth = dto.getPaymentDate()
                .withDayOfMonth(dto.getPaymentDate().lengthOfMonth());

        validateCompensation(dto, employee.getId(), endOfMonth);

        Compensation comp = new Compensation();
        comp.setType(dto.getType());
        comp.setAmount(dto.getAmount());
        comp.setDescription(dto.getDescription());
        comp.setPaymentDate(endOfMonth);
        comp.setEmployee(employee);

        compensationRepo.save(comp);
    }

    private void validateCompensation(CompensationDTO dto,
                                      Long empId,
                                      LocalDate date) {

        BigDecimal amount = dto.getAmount();

        switch (dto.getType()) {

            case SALARY:
                if (compensationRepo.existsByEmployeeIdAndTypeAndPaymentDate(
                        empId, CompensationType.SALARY, date)) {
                    throw new RuntimeException("Salary already exists for this month");
                }
                break;

            case BONUS:
            case COMMISSION:
            case ALLOWANCE:
                if (amount.compareTo(BigDecimal.ZERO) <= 0)
                    throw new RuntimeException("Amount must be greater than zero");
                if (dto.getDescription() == null || dto.getDescription().isEmpty())
                    throw new RuntimeException("Description is required");
                break;

            case ADJUSTMENT:
                if (amount.compareTo(BigDecimal.ZERO) == 0)
                    throw new RuntimeException("Amount cannot be zero");
                if (dto.getDescription() == null || dto.getDescription().isEmpty())
                    throw new RuntimeException("Description is required");
                break;
        }
    }
}
