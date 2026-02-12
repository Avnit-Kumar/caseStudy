package com.ibm.caseStudy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.caseStudy.model.Compensation;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation, Long> {
//    // Story 1.5 - Salary validation
	@Query("SELECT COUNT(c) > 0 FROM Compensation c " + "WHERE c.employee.id = :empId " + "AND c.type = 'Salary' "
			+ "AND FUNCTION('YEAR', c.date) = :year " + "AND FUNCTION('MONTH', c.date) = :month")
	boolean existsSalaryForMonth(Long empId, int year, int month);
//    
//    // Story 1.6 - Monthly totals for ONE employee in date range
    @Query("SELECT YEAR(c.date), MONTH(c.date), SUM(c.amount) " +
           "FROM Compensation c " +
           "WHERE c.employee.id = :empId " +
           "AND c.date BETWEEN :start AND :end " +
           "GROUP BY YEAR(c.date), MONTH(c.date) " +
           "ORDER BY YEAR(c.date), MONTH(c.date)")
    List<Object[]> getMonthlyTotalsForEmployee(Long empId, LocalDate start, LocalDate end);
//    
//    // Story 1.7 - Breakdown for selected employee + month
    @Query("SELECT c FROM Compensation c " +
           "WHERE c.employee.id = :empId " +
           "AND YEAR(c.date) = :year " +
           "AND MONTH(c.date) = :month")
    List<Compensation> getByEmployeeAndMonth(Long empId, int year, int month);
}
