package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterCategoryDTO {

    @NotBlank(message = "Name cannot be blank")
    @JsonProperty("name")
    private String Name; // Note: Java convention is 'name' (lowercase for fields)


    @JsonProperty("description")
    private String Description; // Note: Java convention is 'description' (lowercase for fields)

    public RegisterCategoryDTO(String name, String description) {
        this.Name = name; // Use 'this' for clarity
        this.Description = description; // Use 'this' for clarity
    }

    public RegisterCategoryDTO() {
        // Default no-argument constructor is good practice for DTOs
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