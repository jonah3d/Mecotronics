package com.joe.repository;

import com.joe.dtos.RegisterDepartmentDTO;
import com.joe.dtos.ResponseDepartmentDTO;
import com.joe.dtos.ResponseEmployeeDTO;
import com.joe.models.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository {

    void  addDepartment(RegisterDepartmentDTO department);
    ResponseDepartmentDTO getDepartmentByNumber(String departmentNumber);
    List<ResponseDepartmentDTO> getDepartments();
    List<ResponseEmployeeDTO> getDepartmentEmployees(String departmentNumber);
    ResponseEmployeeDTO getDepartmentHead(String departmentNumber);
    void setDepartmentHead(String employeeNumber, String departmentNumber);
}
