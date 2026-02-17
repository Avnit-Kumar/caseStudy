package com.ibm.caseStudy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.caseStudy.model.Employee;

import java.time.LocalDate;
import java.util.Optional;
//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByFirstNameAndMiddleNameAndLastNameAndDateOfBirth(
            String firstName,
            String middleName,
            String lastName,
            LocalDate dateOfBirth
    );
    
    List<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndPositionContainingIgnoreCase(
            String firstName,
            String lastName,
            String position
    );

}  