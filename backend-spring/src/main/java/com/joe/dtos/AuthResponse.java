package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class AuthResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("username")
    private String username;
    @JsonProperty("roles")
    private List<String> roles;
    @JsonProperty("last_login") // New field for last login date
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Format for output
    private Date lastLogin;

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public AuthResponse(String message, String username, List<String> roles, Date lastLogin) {
        this.message = message;
        this.username = username;
        this.roles = roles;
        this.lastLogin = lastLogin;
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

}
