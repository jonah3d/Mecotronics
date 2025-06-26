package com.joe.controller;

import com.joe.dtos.RegisterDepartmentDTO;
import com.joe.dtos.ResponseDepartmentDTO;
import com.joe.dtos.ResponseEmployeeDTO;
import com.joe.repository.DepartmentRepository;
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
@RequestMapping("/api/shop/departments")
@Validated
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addDepartment(@Valid @RequestBody RegisterDepartmentDTO registerDepartmentDTO){
        if(registerDepartmentDTO ==null){
            return ResponseEntity.badRequest().body("Department is null");
        }

        try{


            departmentRepository.addDepartment(registerDepartmentDTO);
            return ResponseEntity.ok("Department added successfully");

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding department" + e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getDepartments() {
        try{

           List<ResponseDepartmentDTO> departmentDTOList =  departmentRepository.getDepartments();
           if(departmentDTOList==null){
               return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Department list is null");
           }

           return ResponseEntity.ok(departmentDTOList);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting departments" + e.getMessage());
        }
    }

    @GetMapping("/get/{dept_number}")
    public ResponseEntity<?> getDepartment(@PathVariable("dept_number") String deptNumber){

        try{

            if(deptNumber==null){
                return ResponseEntity.badRequest().body("Department number is null");
            }

            ResponseDepartmentDTO responseDepartmentDTO = departmentRepository.getDepartmentByNumber(deptNumber);
            if(responseDepartmentDTO==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not found");
            }

            return ResponseEntity.ok(responseDepartmentDTO);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting department" + e.getMessage());
        }

    }


    @GetMapping("/get/employees/{dept_number}")
    public ResponseEntity<?> getDepartmentEmployees(@PathVariable("dept_number") String deptNumber) {
        try {
            if (deptNumber == null || deptNumber.isEmpty()) {
                return ResponseEntity.badRequest().body("Department number cannot be null or empty");
            }

            List<ResponseEmployeeDTO> employees = departmentRepository.getDepartmentEmployees(deptNumber);
            if (employees == null || employees.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employees found for this department");
            }

            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting department employees: " + e.getMessage());
        }
    }

    @GetMapping("/get/departmentHead/{dept_number}")
    public ResponseEntity<?> getDepartmentHead(@PathVariable("dept_number") String deptNumber) {
        try {
            if (deptNumber == null || deptNumber.isEmpty()) {
                return ResponseEntity.badRequest().body("Department number cannot be null or empty");
            }

            ResponseEmployeeDTO departmentHead = departmentRepository.getDepartmentHead(deptNumber);
            if (departmentHead == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No department head found for this department");
            }

            return ResponseEntity.ok(departmentHead);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting department head: " + e.getMessage());
        }
    }
}
