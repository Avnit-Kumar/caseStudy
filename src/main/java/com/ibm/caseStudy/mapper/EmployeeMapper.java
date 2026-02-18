package com.ibm.caseStudy.mapper;

import com.ibm.caseStudy.dto.EmployeeDTO;
import com.ibm.caseStudy.model.Employee;

public class EmployeeMapper {

	public static Employee toEntity(EmployeeDTO dto) {
		if (dto == null)
			return null;

		Employee employee = new Employee();
		employee.setFirstName(dto.getFirstName());
		employee.setMiddleName(dto.getMiddleName());
		employee.setLastName(dto.getLastName());
		employee.setPosition(dto.getPosition());
		employee.setDateOfBirth(dto.getDateOfBirth());

		return employee;
	}

	public static EmployeeDTO toDTO(Employee employee) {
		if (employee == null)
			return null;

		EmployeeDTO dto = new EmployeeDTO();
		dto.setFirstName(employee.getFirstName());
		dto.setMiddleName(employee.getMiddleName());
		dto.setLastName(employee.getLastName());
		dto.setPosition(employee.getPosition());
		dto.setDateOfBirth(employee.getDateOfBirth());

		return dto;
	}
}