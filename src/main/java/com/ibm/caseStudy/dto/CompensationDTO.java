package com.ibm.caseStudy.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

public class CompensationDTO {
	@NotNull(message = "Employee ID is required")
	private Long employeeId;
	
	@NotBlank(message = "Compensation type is required")
	private String type;
	
	@NotNull(message = "Amount is required")
	private BigDecimal amount;
	
	private String description;
	
	@NotNull(message = "Payment date is required")
	@PastOrPresent(message = "Payment date cannot be in the future")
	private LocalDate date;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
