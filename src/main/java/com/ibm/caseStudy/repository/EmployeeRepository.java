package com.ibm.caseStudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.caseStudy.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}