package com.ibm.caseStudy.Service;



import org.springframework.stereotype.Service;

import com.ibm.caseStudy.exception.EmployeeAlreadyExistsException;
import com.ibm.caseStudy.model.Employee;
import com.ibm.caseStudy.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public void addEmployee(Employee e) {
        repo.findByFirstNameAndMiddleNameAndLastNameAndBirthDate(
                e.getFirstName(),
                e.getMiddleName(),
                e.getLastName(),
                e.getBirthDate()
        ).ifPresent(emp -> {
            throw new EmployeeAlreadyExistsException();
        });

        repo.save(e);
    }
}
