package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDepartmentDTO {


    @JsonProperty("department_number")
    private String departmentNumber;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("description")
    private String description;


    @JsonProperty("employees")
    private List<ResponseEmployeeDTO> employees = new ArrayList<>();

    @JsonProperty("departmentHead")
    private ResponseEmployeeDTO departmentHead;

    public ResponseDepartmentDTO(String departmentNumber, String departmentName, String description, List<ResponseEmployeeDTO> employees, ResponseEmployeeDTO departmentHead) {
        this.departmentNumber = departmentNumber;
        this.departmentName = departmentName;
        this.description = description;

        this.employees = employees;
        this.departmentHead = departmentHead;
    }

    public ResponseDepartmentDTO() {
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public List<ResponseEmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<ResponseEmployeeDTO> employees) {
        this.employees = employees;
    }

    public ResponseEmployeeDTO getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(ResponseEmployeeDTO departmentHead) {
        this.departmentHead = departmentHead;
    }
}
