package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseOccupationSlimDTO {

    @NotBlank(message = "Name cannot be blank")
    @JsonProperty("name")
    private String name;

    public ResponseOccupationSlimDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResponseOccupationSlimDTO() {
    }
}
