package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.GregorianCalendar;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseEmployeeDTO {

    @JsonProperty("emp_num")
    private String employeeNumber;



    public ResponseEmployeeDTO() {
    }

    public ResponseEmployeeDTO(String employeeNumber) {
        this.employeeNumber = employeeNumber;


    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }




}