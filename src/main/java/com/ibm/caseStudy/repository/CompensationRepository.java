package com.ibm.caseStudy.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ibm.caseStudy.model.*;

public interface CompensationRepository extends JpaRepository<Compensation, Long> {

    List<Compensation> findByEmployeeIdAndPaymentDate(Long employeeId, LocalDate paymentDate);

    boolean existsByEmployeeIdAndTypeAndPaymentDate(
            Long employeeId,
            CompensationType type,
            LocalDate paymentDate);
    
    List<Compensation> findByEmployeeIdAndPaymentDateBetween(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate);
    
}