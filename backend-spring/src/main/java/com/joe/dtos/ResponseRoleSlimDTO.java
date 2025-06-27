package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseRoleSlimDTO {
    @JsonProperty("role_name")
    private String name;

    public ResponseRoleSlimDTO(String name) {
        this.name = name;
    }

    public ResponseRoleSlimDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
