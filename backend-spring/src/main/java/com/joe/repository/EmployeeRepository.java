package com.joe.repository;

import com.joe.dtos.RegisterEmployeeDTO;
import com.joe.dtos.ResponseEmployeeDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {

    void addEmployee(RegisterEmployeeDTO employeeDTO);
    ResponseEmployeeDTO getEmployeeByNumber(String empNum);
    List<ResponseEmployeeDTO> getEmployees();
    void deleteEmployee(String empNum);

}
