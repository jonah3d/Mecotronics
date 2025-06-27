package com.joe.controller;

import com.joe.dtos.RegisterEmployeeDTO;
import com.joe.dtos.ResponseEmployeeFullDTO;
import com.joe.models.Employee;
import com.joe.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/remove/{emp_num}")
    public ResponseEntity<?> removeEmployee(@PathVariable("emp_num") String emp_num){
        if(emp_num == null){
            return ResponseEntity.badRequest().body("Employee Number Entered is null");
        }
        try{

            employeeRepository.deleteEmployee(emp_num);
            return ResponseEntity.ok("Employee has been successfully removed");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Removing Employee");
        }
    }

    @GetMapping("/get/{emp_num}")
    public ResponseEntity<?> getEmployeeByNumber(@PathVariable("emp_num") String empNum) {
        if (empNum == null || empNum.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Employee number cannot be null or empty");
        }

        try {
            ResponseEmployeeFullDTO employee = employeeRepository.getEmployeeByNumber(empNum);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving employee");
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllEmployees() {
        try {
            List<ResponseEmployeeFullDTO> employees = employeeRepository.getEmployees();
            if (employees == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Employee list is null");
            }
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving employees");
        }
    }

}
