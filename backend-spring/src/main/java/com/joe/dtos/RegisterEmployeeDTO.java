package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    @NotNull(message = "Hire date cannot be blank")
    @JsonProperty("hire_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    @NotNull(message = "Date Of Birth cannot be blank")
    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @JsonProperty("salary")
    private double salary;

    @JsonProperty("commission")
    private double commission;

    @JsonProperty("image")
    private byte[] image;

    @Valid
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

    @Valid
    @NotNull(message = "Credentials cannot be null")
    @JsonProperty("credentials")
    private RegisterEmployeeLoginDTO credentials;

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public RegisterEmployeeDTO() {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public RegisterHomeAddressDTO getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(RegisterHomeAddressDTO homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public RegisterEmployeeLoginDTO getCredentials() {
        return credentials;
    }

    public void setCredentials(RegisterEmployeeLoginDTO credentials) {
        this.credentials = credentials;
    }

    public RegisterEmployeeDTO(String employeeNumber, String firstName, String lastName, Date hireDate, Date dateOfBirth, double salary, double commission, byte[] image, RegisterHomeAddressDTO homeAddress, String employeeRole, String occupation, String departmentNumber, RegisterEmployeeLoginDTO credentials) {
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.commission = commission;
        this.image = image;
        this.homeAddress = homeAddress;
        this.employeeRole = employeeRole;
        this.occupation = occupation;
        this.departmentNumber = departmentNumber;
        this.credentials = credentials;
    }
}
