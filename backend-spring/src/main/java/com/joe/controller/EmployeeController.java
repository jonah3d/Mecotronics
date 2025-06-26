package com.joe.controller;

import com.joe.dtos.RegisterEmployeeDTO;
import com.joe.models.Employee;
import com.joe.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop/employees")
@Validated
public class EmployeeController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addEmployee(@Valid @RequestBody RegisterEmployeeDTO employee){

        if(employee ==  null){
            return ResponseEntity.badRequest().body("Employee is null");
        }
        try{

            employeeRepository.addEmployee(employee);
            return ResponseEntity.ok("Employee has been successfully added");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Adding Employee");
        }

    }
}
