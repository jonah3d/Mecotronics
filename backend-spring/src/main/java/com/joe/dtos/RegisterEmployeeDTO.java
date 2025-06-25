package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterEmployeeDTO {

    @NotBlank(message = "Employee number cannot be blank")
    @JsonProperty("emp_num")
    private String employeeNumber;

    @NotBlank(message = "First name cannot be blank")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "Hire cannot be blank")
    @JsonProperty("hire_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    @NotBlank(message = "Date Of Birth cannot be blank")
    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @JsonProperty("salary")
    private double salary;

    @JsonProperty("commission")
    private double commission;

    @JsonProperty("image")
    private byte image;

    @JsonProperty("home_address")
    private RegisterHomeAddressDTO homeAddress;

    @NotBlank(message = "Role cannot be blank")
    @JsonProperty("employee_role")
    private String employeeRole;

    @NotBlank(message = "Occupation cannot be blank")
    @JsonProperty("occupation")
    private String occupation;

    @NotBlank(message = "Department cannot be blank")
    @JsonProperty("department_number")
    private String departmentNumber;

    @NotBlank(message = "Credentials cannot be blank")
    @JsonProperty("credentials")
    private RegisterEmployeeLoginDTO credentials;
}
