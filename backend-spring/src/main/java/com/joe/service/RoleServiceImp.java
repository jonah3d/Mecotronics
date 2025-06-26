package com.joe.service;

import com.joe.dtos.RegisterRoleDTO;
import com.joe.dtos.ResponseEmployeeDTO;
import com.joe.dtos.ResponseRoleDTO;
import com.joe.models.Employee;
import com.joe.models.Role;
import com.joe.repository.RoleRepository;
import com.matisse.MtDatabase;
import com.matisse.MtObjectIterator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImp implements RoleRepository {

    private final MtDatabase mtDatabase;
    private final ModelMapper modelMapper;

    public RoleServiceImp(MtDatabase mtDatabase, ModelMapper modelMapper) {
        this.mtDatabase = mtDatabase;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addRole(RegisterRoleDTO registerRoleDTO) {
        try {

            if (!mtDatabase.isTransactionInProgress()) {
                mtDatabase.startTransaction();
            } else {
                throw new Exception("Another transaction is already in progress");
            }

            Role role = new Role(mtDatabase);

            // For some reason we cant do it like this when we mapping outside objects to matisse
            //even if in the modelmapper config we have the mapping manually like below
            // modelMapper.map(registerRoleDTO, role);

            role.setName(registerRoleDTO.getName().toUpperCase());
            role.setDescription(registerRoleDTO.getDescription());
            role.setAccessLevel(registerRoleDTO.getAccessLevel());

            mtDatabase.commit();

        } catch (Exception e) {
            System.err.println("Error adding role: " + e.getMessage());
            try {
                if (mtDatabase.isTransactionInProgress()) {
                    mtDatabase.rollback();
                }
            } catch (Exception rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());
            }
            throw new RuntimeException("Error adding role", e);
        }


    }

    @Override
    public ResponseRoleDTO getRoleByName(String name) {

        if( name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null or empty");
        }
        ResponseRoleDTO responseRoleDTO = null;
        try{

            if(!mtDatabase.isVersionAccessInProgress()){
                mtDatabase.startVersionAccess();
            } else {
                throw new Exception("Another version access is already in progress");
            }

          Role role =   Role.lookupRolename_IDX(mtDatabase, name);
            if(role == null){
                System.out.println("Role with name " + name + " not found.");
                return null;
            }
             responseRoleDTO = modelMapper.map(role, ResponseRoleDTO.class);
            List<ResponseEmployeeDTO> employeeDTOS = new ArrayList<>();
            Employee[] employees = role.getEmployees();
            if (employees != null) {
                for (Employee employee : employees) {
                    ResponseEmployeeDTO responseEmployeeDTO = modelMapper.map(employee, ResponseEmployeeDTO.class);
                    employeeDTOS.add(responseEmployeeDTO);
                }
            }
            responseRoleDTO.setEmployees(employeeDTOS);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                mtDatabase.endVersionAccess();
            } catch (Exception endEx) {
                System.err.println("Error ending version access: " + endEx.getMessage());
            }
        }
        return responseRoleDTO;
    }

    @Override
    public List<ResponseRoleDTO> getRoles() {
        List<ResponseRoleDTO> responseRoleDTOList = new ArrayList<>();

        try {

            mtDatabase.startVersionAccess();
            MtObjectIterator<Role> roleIterator = Role.instanceIterator(mtDatabase);
            if (!roleIterator.hasNext()) {
                System.out.println("No roles found.");
                return responseRoleDTOList;
            }

            while (roleIterator.hasNext()) {
                Role role = roleIterator.next();

                ResponseRoleDTO responseRoleDTO = modelMapper.map(role, ResponseRoleDTO.class);
                List<ResponseEmployeeDTO> employeeDTOS = new ArrayList<>();
                Employee[] employees = role.getEmployees();
                if (employees != null) {
                    for (Employee employee : employees) {
                        ResponseEmployeeDTO responseEmployeeDTO = modelMapper.map(employee, ResponseEmployeeDTO.class);
                        employeeDTOS.add(responseEmployeeDTO);
                    }
                }
                responseRoleDTO.setEmployees(employeeDTOS);
                responseRoleDTOList.add(responseRoleDTO);
            }

        } catch (Exception e) {
            System.err.println("Error getting roles: " + e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                mtDatabase.endVersionAccess();
            } catch (Exception endEx) {
                System.err.println("Error ending version access: " + endEx.getMessage());
            }
        }

        return responseRoleDTOList;
    }
}
