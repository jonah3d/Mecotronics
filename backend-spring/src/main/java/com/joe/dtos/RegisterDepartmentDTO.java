package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDepartmentDTO {


    @NotBlank(message = "Department number cannot be blank or less than 3 characters")
    @JsonProperty("department_number")
    @Length( min = 3, max = 3)
    private String departmentNumber;

    @NotBlank(message = "Department name cannot be blank")
    @JsonProperty("department_name")
    private String departmentName;


    @JsonProperty("description")
    private String description;

    @JsonProperty("department_header_id")
    private int headerId;


    public RegisterDepartmentDTO(String departmentNumber, String departmentName, String description, int headerId) {
        this.departmentNumber = departmentNumber;
        this.departmentName = departmentName;
        this.description = description;
        this.headerId = headerId;
    }

    public RegisterDepartmentDTO() {
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

    public int getHeaderId() {
        return headerId;
    }

    public void setHeaderId(int headerId) {
        this.headerId = headerId;
    }
}
