package com.joe.service;

import com.joe.dtos.RegisterDepartmentDTO;
import com.joe.dtos.ResponseDepartmentDTO;
import com.joe.dtos.ResponseEmployeeDTO;
import com.joe.models.Department;
import com.joe.models.Employee;
import com.joe.models.Role;
import com.joe.repository.DepartmentRepository;
import com.matisse.MtDatabase;
import com.matisse.MtObjectIterator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentRepository {

    private final ModelMapper modelMapper;
    private final MtDatabase mtDatabase;

    public DepartmentServiceImp(ModelMapper modelMapper, MtDatabase mtDatabase) {
        this.modelMapper = modelMapper;
        this.mtDatabase = mtDatabase;
    }

    @Override
    public void addDepartment(RegisterDepartmentDTO department) {

        try {

            if (!mtDatabase.isTransactionInProgress()) {
                mtDatabase.startTransaction();
            } else {
                throw new Exception("Another Transaction is in progress");
            }

            Department mtDepartment = new Department(mtDatabase);

            mtDepartment.setDepartmentName(department.getDepartmentName());
            mtDepartment.setDepartmentNumber(department.getDepartmentNumber());
            mtDepartment.setDescription(department.getDescription());

            mtDatabase.commit();

        } catch (Exception e) {
            System.err.println("Error adding department: " + e.getMessage());
            try {
                if (mtDatabase.isTransactionInProgress()) {
                    mtDatabase.rollback();
                }
            } catch (Exception rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());
            }
            throw new RuntimeException("Error adding department", e);
        }


    }

    @Override
    public ResponseDepartmentDTO getDepartmentByNumber(String departmentNumber) {

        if (departmentNumber == null || departmentNumber.isEmpty()) {
            throw new IllegalArgumentException("Department number cannot be null or empty");
        }

        ResponseDepartmentDTO responseDepartmentDTO = null;

        try {
            if (!mtDatabase.isVersionAccessInProgress()) {
                mtDatabase.startVersionAccess();
            } else {
                throw new Exception("Another version access is already in progress");
            }

            Department department = Department.lookupDeptNo_IDX(mtDatabase, departmentNumber);
            if (department == null) {
                System.out.println("Department with number " + departmentNumber + " not found.");
                return null;
            }

            responseDepartmentDTO = modelMapper.map(department, ResponseDepartmentDTO.class);

            List<ResponseEmployeeDTO> employeeDTOS = new ArrayList<>();
            Employee[] employees = department.getMembers();
            if (employees != null) {
                for (Employee employee : employees) {
                    ResponseEmployeeDTO responseEmployeeDTO = modelMapper.map(employee, ResponseEmployeeDTO.class);
                    employeeDTOS.add(responseEmployeeDTO);
                }
            }
            responseDepartmentDTO.setEmployees(employeeDTOS);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                mtDatabase.endVersionAccess();
            } catch (Exception endEx) {
                System.err.println("Error ending version access: " + endEx.getMessage());
            }
        }

        return responseDepartmentDTO;
    }

    @Override
    public List<ResponseDepartmentDTO> getDepartments() {
        List<ResponseDepartmentDTO> departmentDTOs = new ArrayList<>();

        try {

            if (!mtDatabase.isVersionAccessInProgress()) {
                mtDatabase.startVersionAccess();
            } else {
                throw new Exception("Another Version Access is in progress");

            }

            MtObjectIterator<Department> mtDepartment = Department.instanceIterator(mtDatabase);
            if (!mtDepartment.hasNext()) {
                throw new Exception("No more departments");
            }

            while (mtDepartment.hasNext()) {
                Department department = mtDepartment.next();

                ResponseDepartmentDTO departmentDTO = modelMapper.map(department, ResponseDepartmentDTO.class);

                List<ResponseEmployeeDTO> responseEmployeeDTOs = new ArrayList<>();
                Employee[] employees = department.getMembers();
                if (employees != null) {
                    for (Employee employee : employees) {
                        responseEmployeeDTOs.add(modelMapper.map(employee, ResponseEmployeeDTO.class));
                    }
                }
                departmentDTO.setEmployees(responseEmployeeDTOs);
                departmentDTOs.add(departmentDTO);
            }


        } catch (Exception e) {
            throw new RuntimeException("Error retrieving departments: " + e.getMessage(), e);
        } finally {
            try {
                mtDatabase.endVersionAccess();
            } catch (Exception endEx) {
                System.err.println("Error ending version access: " + endEx.getMessage());
            }
        }


        return departmentDTOs;
    }

    @Override
    public List<ResponseEmployeeDTO> getDepartmentEmployees(String departmentNumber) {
        if (departmentNumber == null || departmentNumber.isEmpty()) {
            throw new IllegalArgumentException("Department number cannot be null or empty");
        }

        List<ResponseEmployeeDTO> responseEmployeeDTOs = new ArrayList<>();

        try {

            if (!mtDatabase.isVersionAccessInProgress()) {
                mtDatabase.startVersionAccess();
            } else {
                throw new Exception("Another Version Access is in progress");

            }

            Department department = Department.lookupDeptNo_IDX(mtDatabase, departmentNumber);
            if (department == null) {
                System.out.println("Department with number " + departmentNumber + " not found.");
                return null;
            }

            Employee[] employees = department.getMembers();
            if (employees != null) {
                for (Employee employee : employees) {
                    ResponseEmployeeDTO responseEmployeeDTO = modelMapper.map(employee, ResponseEmployeeDTO.class);
                    responseEmployeeDTOs.add(responseEmployeeDTO);
                }
            } else {
                System.out.println("No employees found in department " + departmentNumber);
                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving department employees: " + e.getMessage(), e);
        } finally {
            try {
                mtDatabase.endVersionAccess();
            } catch (Exception endEx) {
                System.err.println("Error ending version access: " + endEx.getMessage());
            }
        }


        return responseEmployeeDTOs;
    }

    @Override
    public ResponseEmployeeDTO getDepartmentHead(String departmentNumber) {
        if (departmentNumber == null || departmentNumber.isEmpty()) {
            throw new IllegalArgumentException("Department number cannot be null or empty");
        }

        ResponseEmployeeDTO responseEmployeeDTO = null;

        try {

            if (!mtDatabase.isVersionAccessInProgress()) {
                mtDatabase.startVersionAccess();
            } else {
                throw new Exception("Another Version Access is in progress");

            }

            Department department = Department.lookupDeptNo_IDX(mtDatabase, departmentNumber);
            if (department == null) {
                System.out.println("Department with number " + departmentNumber + " not found.");
                return null;
            }

         Employee dept_head =    department.getDepartmentHead();
            if(dept_head == null){

                return null;
            }
            responseEmployeeDTO = modelMapper.map(dept_head, ResponseEmployeeDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving department employees: " + e.getMessage(), e);
        } finally {
            try {
                mtDatabase.endVersionAccess();
            } catch (Exception endEx) {
                System.err.println("Error ending version access: " + endEx.getMessage());
            }
        }
        return responseEmployeeDTO;
    }

    @Override
    public void setDepartmentHead(String employeeNumber, String departmentNumber) {

    }
}
