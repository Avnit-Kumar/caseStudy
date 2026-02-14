package com.ibm.caseStudy.exception;

public class EmployeeAlreadyExistsException extends RuntimeException {

	public EmployeeAlreadyExistsException() {
        super("Employee already exists with the same name and birth date");
    }

    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}