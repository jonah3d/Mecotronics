package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterOccupationDTO {

    @NotBlank(message = "Name cannot be blank")
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public RegisterOccupationDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public RegisterOccupationDTO() {

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
}
