package com.ibm.caseStudy.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;

import org.springframework.format.annotation.DateTimeFormat;

import com.ibm.caseStudy.model.CompensationType;

public class CompensationDTO {

	private Long id;

	@NotNull(message = "Type is required")
	private CompensationType type;

	@NotNull(message = "Amount is required")
	private BigDecimal amount;

	private String description;

	@NotNull(message = "Payment date is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDate;

	private Long employeeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CompensationType getType() {
		return type;
	}

	public void setType(CompensationType type) {
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

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
}