package com.ibm.caseStudy.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only alphabets and no spaces")
    private String firstName;

    @Size(max = 50, message = "Middle name cannot exceed 50 characters")
    @Pattern(regexp = "^[A-Za-z ]*$", message = "Middle name must contain only alphabets")
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only alphabets and no spaces")
    private String lastName;

    @NotBlank(message = "Position is required")
    @Size(max = 100, message = "Position cannot exceed 100 characters")
    @Pattern(regexp = "^[A-Za-z]{2}[A-Za-z0-9 ]*$", message = "Position must start with 2 alphabets and can contain letters or numbers")
    private String position;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Birth date must be in the past")
    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName != null ? firstName.trim() : null;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName != null ? middleName.trim() : null;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName != null ? lastName.trim() : null;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position != null ? position.trim() : null;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
