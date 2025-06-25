package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseRoleDTO {
    @NotBlank(message = "Name cannot be blank")
    @JsonProperty("role_name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("access_level")
    private int accessLevel;

    @JsonProperty("employees")
    private List<ResponseEmployeeDTO> employees = new ArrayList<>();

    public ResponseRoleDTO() {
    }

    public ResponseRoleDTO(String name, String description, int accessLevel, List<ResponseEmployeeDTO> employees) {
        this.name = name;
        this.description = description;
        this.accessLevel = accessLevel;
        this.employees = employees;
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

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public List<ResponseEmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<ResponseEmployeeDTO> employees) {
        this.employees = employees;
    }
}
