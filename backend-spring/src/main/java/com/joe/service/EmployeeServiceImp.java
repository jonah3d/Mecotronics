package com.joe.service;


import com.joe.dtos.*;
import com.joe.models.*;
import com.joe.repository.EmployeeRepository;
import com.matisse.MtDatabase;
import com.matisse.MtObjectIterator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeServiceImp implements EmployeeRepository {

    private final ModelMapper modelMapper;
    private final MtDatabase mtDatabase;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImp(ModelMapper modelMapper, MtDatabase mtDatabase, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.mtDatabase = mtDatabase;
        this.passwordEncoder = passwordEncoder;
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


            employee.setEmployeeNumber(getEmpNumInc());
            employee.setFirstname(employeeDTO.getFirstName());
            employee.setSurname(employeeDTO.getSurname());


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


            Occupation emp_occupation = Occupation.lookupOccupationName_IDX(mtDatabase, employeeDTO.getJob());
            if (emp_occupation == null) {
                throw new IllegalArgumentException("Occupation '" + employeeDTO.getJob() + "' Not Found In The Database.");
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
                employeeLogin.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
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
    public ResponseEmployeeFullDTO getEmployeeByNumber(String empNum) {
        try {
            if (!mtDatabase.isTransactionInProgress()) {
                mtDatabase.startTransaction();
            } else {
                throw new IllegalStateException("Another Matisse transaction is already in progress.");
            }

            Employee employee = Employee.lookupEmpNo_IDX(mtDatabase, empNum);

            if (employee == null) {
                throw new IllegalArgumentException("Employee with number '" + empNum + "' not found.");
            }

            ResponseEmployeeFullDTO dto = modelMapper.map(employee, ResponseEmployeeFullDTO.class);
            mtDatabase.commit();
            return dto;

        } catch (Exception e) {
            System.err.println("Error retrieving employee: " + e.getMessage());

            try {
                if (mtDatabase.isTransactionInProgress()) {
                    mtDatabase.rollback();
                }
            } catch (Exception rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());
            }
            throw new RuntimeException("Error retrieving employee", e);
        }
    }

    private int fetchLatestEmpNum() {
        int nextEmpNum = 0;
        try {
//            if (!mtDatabase.isTransactionInProgress()) {
//                mtDatabase.startTransaction();
//            } else {
//                throw new IllegalStateException("Another Matisse transaction is already in progress.");
//            }
            MtObjectIterator<Employee> emp = Employee.empNo_IDXIterator(mtDatabase, null, null);
            if (!emp.hasNext()) {
                return nextEmpNum = nextEmpNum + 1;
            }
            Employee lastemp = null;
            while (emp.hasNext()) {
                lastemp = emp.next();
            }

            String inputEmpnum = lastemp.getEmployeeNumber();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(inputEmpnum);

            if (matcher.find()) {
                String number = matcher.group();
                int numberInt = Integer.parseInt(number);
                numberInt = numberInt + 1;
                nextEmpNum = numberInt;
            }
        } catch (Exception e) {
            System.err.println("Error retrieving lastnum: " + e.getMessage());
            try {
                if (mtDatabase.isTransactionInProgress()) {
                    mtDatabase.rollback();
                }
            } catch (Exception rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());
            }
            throw new RuntimeException("Error retrieving employee lastnum", e);
        }
        return nextEmpNum;
    }

    public String getEmpNumInc() {
        String employeenum = "emp_" + String.valueOf(fetchLatestEmpNum());
        return employeenum;
    }

    @Override
    public List<ResponseEmployeeFullDTO> getEmployees() {
        try {
            if (!mtDatabase.isTransactionInProgress()) {
                mtDatabase.startTransaction();
            } else {
                throw new IllegalStateException("Another Matisse transaction is already in progress.");
            }


            MtObjectIterator<Employee> allEmployees = Employee.instanceIterator(mtDatabase);
            List<ResponseEmployeeFullDTO> dtoList = new ArrayList<>();

            if (!allEmployees.hasNext()) {
              return null;
            }

            while (allEmployees.hasNext()) {
                Employee employee = allEmployees.next();

                ResponseEmployeeFullDTO responseEmployeeFullDTO = modelMapper.map(employee, ResponseEmployeeFullDTO.class);

                dtoList.add(responseEmployeeFullDTO);
            }


            mtDatabase.commit();
            return dtoList;

        } catch (Exception e) {
            System.err.println("Error retrieving employees: " + e.getMessage());
            try {
                if (mtDatabase.isTransactionInProgress()) {
                    mtDatabase.rollback();
                }
            } catch (Exception rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());
            }
            throw new RuntimeException("Error retrieving employees", e);
        }
    }


    @Override
    public void deleteEmployee(String empNum) {

        try {

            if (!mtDatabase.isTransactionInProgress()) {
                mtDatabase.startTransaction();
            } else {
                throw new IllegalStateException("Another Matisse transaction is already in progress.");
            }


            Employee emp = Employee.lookupEmpNo_IDX(mtDatabase, empNum);
            if (emp == null) {
                throw new IllegalArgumentException("Employee '" + empNum + "' Not Found In The Database.");
            } else {

                emp.deepRemove();


                mtDatabase.commit();
            }

        } catch (Exception e) {
            System.err.println("Error removing employee: " + e.getMessage());
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
}
