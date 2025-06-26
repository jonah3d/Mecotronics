package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseEmployeeDTO {
    @NotBlank(message = "Employee number cannot be blank")
    @JsonProperty("emp_num")
    private String employeeNumber;

    @NotBlank(message = "First name cannot be blank")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @JsonProperty("surname")
    private String surname;

    public ResponseEmployeeDTO(String employeeNumber, String firstName, String surname) {
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.surname = surname;
    }

    public ResponseEmployeeDTO() {
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
