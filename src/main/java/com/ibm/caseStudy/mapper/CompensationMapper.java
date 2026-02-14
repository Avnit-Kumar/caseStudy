package com.ibm.caseStudy.mapper;

import com.ibm.caseStudy.dto.CompensationDTO;
import com.ibm.caseStudy.model.Compensation;
import com.ibm.caseStudy.model.Employee;

public class CompensationMapper {
	public static Compensation toEntity(CompensationDTO dto, Employee employee) {
        Compensation comp = new Compensation();
        comp.setEmployee(employee);
        comp.setType(dto.getType());
        comp.setAmount(dto.getAmount());
        comp.setDescription(dto.getDescription());
        comp.setDate(dto.getDate());
        return comp;
    }
    // Entity → DTO
    public static CompensationDTO toDTO(Compensation comp) {
        CompensationDTO dto = new CompensationDTO();
        if (comp.getEmployee() != null) {
            dto.setEmployeeId(comp.getEmployee().getId());
        }
        dto.setEmployeeId(comp.getEmployee().getId());
        dto.setType(comp.getType());
        dto.setAmount(comp.getAmount());
        dto.setDescription(comp.getDescription());
        dto.setDate(comp.getDate());
        return dto;
    }
}
