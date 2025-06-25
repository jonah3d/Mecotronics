package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRoleDTO {


    @NotBlank(message = "Name cannot be blank")
    @JsonProperty("role_name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("access_level")
    private int accessLevel;


    public RegisterRoleDTO(String name, String description, int accessLevel) {
        this.name = name;
        this.description = description;
        this.accessLevel = accessLevel;
    }

    public RegisterRoleDTO() {

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
}
