package com.ibm.caseStudy.service;

import java.util.List;
import com.ibm.caseStudy.dto.EmployeeDTO;

public interface EmployeeService {

    void addEmployee(EmployeeDTO dto);

    List<EmployeeDTO> searchEmployees(String firstName, String lastName, String position);

    EmployeeDTO getEmployeeById(Long id);

    void updateEmployee(EmployeeDTO dto);
}
