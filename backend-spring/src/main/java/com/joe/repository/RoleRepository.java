package com.joe.repository;

import com.joe.dtos.RegisterRoleDTO;
import com.joe.dtos.ResponseRoleDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository {

    void addRole(RegisterRoleDTO registerRoleDTO);
    ResponseRoleDTO getRoleByName(String name);
    List<ResponseRoleDTO> getRoles();

}
