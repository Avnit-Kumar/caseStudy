package com.ibm.caseStudy.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.caseStudy.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByFirstNameAndMiddleNameAndLastNameAndDateOfBirth(String firstName, String middleName,
			String lastName, LocalDate dateOfBirth);

	List<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndPositionContainingIgnoreCase(
			String firstName, String lastName, String position);

}