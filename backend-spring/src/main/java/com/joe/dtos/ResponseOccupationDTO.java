package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseOccupationDTO {

    @NotBlank(message = "Name cannot be blank")
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("employees")
    private List<ResponseEmployeeDTO> employees = new ArrayList<>();

    public ResponseOccupationDTO(String name, String description, List<ResponseEmployeeDTO> employees) {
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    public ResponseOccupationDTO() {
        // Default constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
