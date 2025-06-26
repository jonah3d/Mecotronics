package com.joe.service;


import com.joe.dtos.RegisterEmployeeDTO;
import com.joe.dtos.RegisterEmployeeLoginDTO;
import com.joe.dtos.RegisterHomeAddressDTO;
import com.joe.dtos.ResponseEmployeeDTO;
import com.joe.models.*;
import com.joe.repository.EmployeeRepository;
import com.matisse.MtDatabase;
import com.matisse.reflect.MtAttribute;
import com.matisse.reflect.MtType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service

public class EmployeeServiceImp implements EmployeeRepository {

    private final ModelMapper modelMapper;
    private final MtDatabase mtDatabase;

    public EmployeeServiceImp(ModelMapper modelMapper, MtDatabase mtDatabase) {
        this.modelMapper = modelMapper;
        this.mtDatabase = mtDatabase;
    }

    @Override
    public void addEmployee(RegisterEmployeeDTO employeeDTO) {
        try {

            if (!mtDatabase.isTransactionInProgress()) {
                mtDatabase.startTransaction();
            } else {
                throw new IllegalStateException("Another Matisse transaction is already in progress.");
            }


            if (employeeDTO == null) {
                throw new IllegalArgumentException("Employee DTO cannot be null");
            }

            Employee employee = new Employee(mtDatabase);


            employee.setEmployeeNumber(employeeDTO.getEmployeeNumber());
            employee.setFirstname(employeeDTO.getFirstName());
            employee.setSurname(employeeDTO.getLastName());


            GregorianCalendar calendar = new GregorianCalendar();


            Date hireDateDTO = employeeDTO.getHireDate();
            if (hireDateDTO != null) {
                calendar.setTime(hireDateDTO);
                employee.setHireDate(calendar);
            } else {
               throw new IllegalArgumentException("Hire date cannot be null");
            }


            Date birthDateDTO = employeeDTO.getDateOfBirth();
            if (birthDateDTO != null) {
                calendar.setTime(birthDateDTO);
                employee.setDateOfBirth(calendar);
            } else {
               throw new IllegalArgumentException("Birth date cannot be null");
            }


            employee.setSalary(employeeDTO.getSalary());
            employee.setCommission(employeeDTO.getCommission());

            if (employeeDTO.getImage() != null && employeeDTO.getImage().length > 0) {

                com.matisse.reflect.MtAttribute photoAttribute = Employee.getPhotoAttribute(mtDatabase);
                employee.setImage(photoAttribute, employeeDTO.getImage());

            } else {
                employee.removePhoto();
            }



            RegisterHomeAddressDTO homeAddressDTO = employeeDTO.getHomeAddress();
            if (homeAddressDTO != null) {
                Address address = new Address(mtDatabase);
                address.setDirecctionOne(homeAddressDTO.getDirectionOne());
                address.setDirecctionTwo(homeAddressDTO.getDirectionTwo());
                address.setCity(homeAddressDTO.getCity());
                address.setState(homeAddressDTO.getState());
                address.setPostalCode(homeAddressDTO.getPostalCode());
                address.setCountry(homeAddressDTO.getCountry());
                employee.setHomeAddress(address);
            } else {
                employee.clearHomeAddress();
            }



            Role emp_role = Role.lookupRolename_IDX(mtDatabase, employeeDTO.getEmployeeRole());
            if (emp_role == null) {
                throw new IllegalArgumentException("Employee Role '" + employeeDTO.getEmployeeRole() + "' Not Found In The Database.");
            }
            employee.setEmployeeRole(emp_role);


            Occupation emp_occupation = Occupation.lookupOccupationName_IDX(mtDatabase, employeeDTO.getOccupation());
            if (emp_occupation == null) {
                throw new IllegalArgumentException("Occupation '" + employeeDTO.getOccupation() + "' Not Found In The Database.");
            }
            employee.setJob(emp_occupation);


            Department emp_departmet = Department.lookupDeptNo_IDX(mtDatabase, employeeDTO.getDepartmentNumber());
            if (emp_departmet == null) {
                throw new IllegalArgumentException("Department '" + employeeDTO.getDepartmentNumber() + "' Not Found In The Database.");
            }
            employee.setMemberOf(emp_departmet);


            RegisterEmployeeLoginDTO loginDTO = employeeDTO.getCredentials();
            if (loginDTO != null) {
                EmployeeLogin employeeLogin = new EmployeeLogin(mtDatabase);
                employeeLogin.setUsername(loginDTO.getUsername());
                employeeLogin.setPassword(loginDTO.getPassword());
                employee.setCredentials(employeeLogin);
            } else {
              throw new IllegalArgumentException("Credentials cannot be null");
            }

            mtDatabase.commit();

        } catch (Exception e) {
            System.err.println("Error adding employee: " + e.getMessage());
            e.printStackTrace();


            try {
                if (mtDatabase.isTransactionInProgress()) {
                    mtDatabase.rollback();
                }
            } catch (Exception rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());

            }

            throw new RuntimeException("Error adding employee", e);
        }
    }

    @Override
    public ResponseEmployeeDTO getEmployeeByNumber(String empNum) {
        return null;
    }

    @Override
    public List<ResponseEmployeeDTO> getEmployees() {
        return List.of();
    }

    @Override
    public void deleteEmployee(String empNum) {

    }
}
