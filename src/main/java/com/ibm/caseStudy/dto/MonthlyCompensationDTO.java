
package com.ibm.caseStudy.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

public class MonthlyCompensationDTO {

	private YearMonth month;
	private BigDecimal total;

	public MonthlyCompensationDTO(YearMonth month, BigDecimal total) {
		this.month = month;
		this.total = total;
	}

	public YearMonth getMonth() {
		return month;
	}

	public BigDecimal getTotal() {
		return total;
	}
}
