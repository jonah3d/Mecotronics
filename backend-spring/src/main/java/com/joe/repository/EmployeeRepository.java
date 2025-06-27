package com.joe.repository;

import com.joe.dtos.RegisterEmployeeDTO;
import com.joe.dtos.ResponseEmployeeDTO;
import com.joe.dtos.ResponseEmployeeFullDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {

    void addEmployee(RegisterEmployeeDTO employeeDTO);
    ResponseEmployeeFullDTO getEmployeeByNumber(String empNum);
    List<ResponseEmployeeFullDTO> getEmployees();
    void deleteEmployee(String empNum);

}
