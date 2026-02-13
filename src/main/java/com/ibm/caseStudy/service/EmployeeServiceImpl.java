package com.ibm.caseStudy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.caseStudy.dto.EmployeeDTO;
import com.ibm.caseStudy.exception.EmployeeAlreadyExistsException;
import com.ibm.caseStudy.model.Employee;
import com.ibm.caseStudy.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

 

    @Override
    public void addEmployee(EmployeeDTO dto) {

        repo.findByFirstNameAndMiddleNameAndLastNameAndDateOfBirth(
                dto.getFirstName(),
                dto.getMiddleName(),
                dto.getLastName(),
                dto.getDateOfBirth()
        ).ifPresent(emp -> {
            throw new EmployeeAlreadyExistsException();
        });

        Employee employee = convertToEntity(dto);
        repo.save(employee);
    }


    @Override
    public List<EmployeeDTO> searchEmployees(String firstName, String lastName, String position) {

        return repo
                .findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndPositionContainingIgnoreCase(
                        firstName == null ? "" : firstName,
                        lastName == null ? "" : lastName,
                        position == null ? "" : position
                )
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public EmployeeDTO getEmployeeById(Long id) {

        Employee employee = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return convertToDTO(employee);
    }

  

    @Override
    public void updateEmployee(EmployeeDTO dto) {

        repo.findByFirstNameAndMiddleNameAndLastNameAndDateOfBirth(
                dto.getFirstName(),
                dto.getMiddleName(),
                dto.getLastName(),
                dto.getDateOfBirth()
        ).ifPresent(existing -> {
            if (!existing.getId().equals(dto.getId())) {
                throw new EmployeeAlreadyExistsException(
                        "Another employee already exists with same details"
                );
            }
        });

        Employee employee = convertToEntity(dto);
        repo.save(employee);
    }



    private Employee convertToEntity(EmployeeDTO dto) {

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setMiddleName(dto.getMiddleName());
        employee.setLastName(dto.getLastName());
        employee.setPosition(dto.getPosition());
        employee.setDateOfBirth(dto.getDateOfBirth());

        return employee;
    }

    private EmployeeDTO convertToDTO(Employee employee) {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setMiddleName(employee.getMiddleName());
        dto.setLastName(employee.getLastName());
        dto.setPosition(employee.getPosition());
        dto.setDateOfBirth(employee.getDateOfBirth());

        return dto;
    }
}