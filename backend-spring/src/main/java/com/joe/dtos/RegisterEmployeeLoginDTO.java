package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterEmployeeLoginDTO {

    @NotBlank(message = "Username cannot be blank")
    @JsonProperty("username")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @JsonProperty("password")
    private String password;

    public RegisterEmployeeLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public RegisterEmployeeLoginDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
