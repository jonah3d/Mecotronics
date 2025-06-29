package com.joe.controller;


import com.joe.dtos.AuthResponse;
import com.joe.dtos.LoginRequest;
import com.joe.dtos.ResponseEmployeeLoginDTO;
import com.joe.service.EmployeeServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shop/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeServiceImp employeeService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            // Attempt to authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // If authentication is successful, set it in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            employeeService.updateEmployeeLastLogin(loginRequest.getUsername());

            // Get roles for the response
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            Date now = new Date();
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(now);


            AuthResponse authResponse = new AuthResponse("User logged in successfully!", loginRequest.getUsername(), roles, gc.getTime());
            ResponseEmployeeLoginDTO responseEmployeeLoginDTO = new ResponseEmployeeLoginDTO();
            return ResponseEntity.ok(authResponse);

        } catch (AuthenticationException e) {
            System.err.println("Authentication failed for user " + loginRequest.getUsername() + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Invalid username or password"));
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthResponse("An unexpected error occurred during login."));
        }
    }


}