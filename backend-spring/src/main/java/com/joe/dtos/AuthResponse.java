package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AuthResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("username")
    private String username;
    @JsonProperty("roles")
    private List<String> roles;
    // You might add a JWT token here if you implement JWT authentication
    // @JsonProperty("token")
    // private String token;

    public AuthResponse(String message, String username, List<String> roles) {
        this.message = message;
        this.username = username;
        this.roles = roles;
    }

    public AuthResponse(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
    // Getter/Setter for token if added
}
