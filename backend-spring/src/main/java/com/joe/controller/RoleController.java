package com.joe.controller;

import com.joe.dtos.RegisterRoleDTO;
import com.joe.dtos.ResponseRoleDTO;
import com.joe.repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/roles")
@Validated
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRole(@Valid @RequestBody RegisterRoleDTO registerRoleDTO) {

        try{

            if(registerRoleDTO == null) {
                return ResponseEntity.badRequest().body("Role DATA cannot be null");
            }

            roleRepository.addRole(registerRoleDTO);

            return ResponseEntity.ok("Role added successfully");

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("")
    public ResponseEntity<?> getRoles() {
        try {
            List<ResponseRoleDTO> roles = roleRepository.getRoles();
            if (roles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No roles found");
            }

            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving roles: " + e.getMessage());
        }
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<?> getRole(@PathVariable String name) {
        try {
            ResponseRoleDTO role = roleRepository.getRoleByName(name.toUpperCase());
            if (role == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
            }
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving role: " + e.getMessage());
        }
    }

}
