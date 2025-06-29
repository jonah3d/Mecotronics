package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.GregorianCalendar;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseEmployeeLoginDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("last_login")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;

    public ResponseEmployeeLoginDTO() {
    }

    public ResponseEmployeeLoginDTO(String username, Date lastLogin) {
        this.username = username;
        this.lastLogin = lastLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(GregorianCalendar lastLogin) {
        if(lastLogin != null) {
            this.lastLogin = lastLogin.getTime();
        }
    }


}
