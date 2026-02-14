package com.ibm.caseStudy.exception;

public class AmountNegativeException extends RuntimeException {
	public AmountNegativeException(String message) {
		super(message);
	}
}
