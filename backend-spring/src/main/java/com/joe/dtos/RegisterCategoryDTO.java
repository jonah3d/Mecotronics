package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterCategoryDTO {

    @NotBlank(message = "Name cannot be blank")
    @JsonProperty("name")
    private String Name;

    @JsonProperty("description")
    private String Description;
    public RegisterCategoryDTO(String name, String description) {
        this.Name = name;
        this.Description = description;
    }

    public RegisterCategoryDTO() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}