package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.GregorianCalendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEmployeeFullDTO {
    @JsonProperty("emp_num")
    private String employeeNumber;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("surname")
    private String surname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("hire_date")
    private Date hireDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("salary")
    private Double salary;

    @JsonProperty("commission")
    private Double commission;

    @JsonProperty("image")
    private byte[] image;

    @JsonProperty("home_address")
    private ResponseHomeAddressDTO homeAddress;

    @JsonProperty("employee_role")
    private ResponseRoleSlimDTO employeeRole;

    @JsonProperty("job")
    private ResponseOccupationSlimDTO job;

    @JsonProperty("department_number")
    private ResponseDepartmentDTO department;

    @Valid
    @JsonProperty("credentials")
    private ResponseEmployeeLoginDTO credentials;

    public ResponseEmployeeFullDTO() {
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

    public Date getHireDate() {
        return hireDate;
    }


    public void setHireDate(GregorianCalendar hireCalendar) {
        if (hireCalendar != null) {
            this.hireDate = hireCalendar.getTime();
        }
    }

    public void setDateOfBirth(GregorianCalendar birthCalendar) {
        if (birthCalendar != null) {
            this.dateOfBirth = birthCalendar.getTime();
        }
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }



    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ResponseHomeAddressDTO getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(ResponseHomeAddressDTO homeAddress) {
        this.homeAddress = homeAddress;
    }

    public ResponseRoleSlimDTO getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(ResponseRoleSlimDTO employeeRole) {
        this.employeeRole = employeeRole;
    }

    public ResponseOccupationSlimDTO getJob() {
        return job;
    }

    public void setJob(ResponseOccupationSlimDTO job) {
        this.job = job;
    }

    public ResponseDepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(ResponseDepartmentDTO department) {
        this.department = department;
    }

    public ResponseEmployeeLoginDTO getCredentials() {
        return credentials;
    }

    public void setCredentials(ResponseEmployeeLoginDTO credentials) {
        this.credentials = credentials;
    }
}
