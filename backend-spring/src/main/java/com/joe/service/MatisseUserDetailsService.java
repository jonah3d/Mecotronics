package com.joe.service;

import com.joe.dtos.ResponseEmployeeFullDTO;
import com.joe.models.Employee;
import com.joe.models.EmployeeLogin;
import com.joe.models.Role;
import com.joe.repository.EmployeeRepository;
import com.matisse.MtDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatisseUserDetailsService implements UserDetailsService {

    private final MtDatabase mtDatabase;

    public MatisseUserDetailsService(MtDatabase mtDatabase) {
        this.mtDatabase = mtDatabase;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeLogin employeeLogin = null;
        Employee employee = null;
        try {
            // Start a read transaction
            mtDatabase.startVersionAccess();

            // Find the EmployeeLogin by username
            // Assuming EmployeeLogin has a lookup index for Username (e.g., lookupUsername_IDX)
            employeeLogin = EmployeeLogin.lookupUsername_IDX(mtDatabase, username);

            if (employeeLogin == null) {
                throw new UsernameNotFoundException("User not found: " + username);
            }

            // Get the associated Employee and their Role
            // Assuming EmployeeLogin has a relationship 'Employee' inverse Employee::Credentials
            employee = employeeLogin.getEmployee();

            if (employee == null) {
                throw new UsernameNotFoundException("Employee details not found for user: " + username);
            }

            // Get the Role of the employee
            // Assuming Employee has a relationship 'EmployeeRole' to Role entity
            Role employeeRole = employee.getEmployeeRole();

            if (employeeRole == null) {
                // Handle cases where an employee might not have a role (e.g., assign a default role)
                throw new UsernameNotFoundException("Role not assigned for user: " + username);
            }

            // Create authorities from the employee's role
            List<GrantedAuthority> authorities = new ArrayList<>();
            // Spring Security roles should be prefixed with "ROLE_"
            authorities.add(new SimpleGrantedAuthority("ROLE_" + employeeRole.getName().toUpperCase()));

            // Build Spring Security's UserDetails object
            return new User(
                    employeeLogin.getUsername(),
                    employeeLogin.getPassword(), // The password from DB (should be hashed)
                    authorities
            );

        } catch (UsernameNotFoundException e) {
            // Re-throw specific exception for Spring Security to handle
            throw e;
        } catch (Exception e) {
            System.err.println("Error loading user by username from Matisse: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error loading user from database", e);
        } finally {
            try {
                // End the read transaction
                mtDatabase.endVersionAccess();
            } catch (Exception endEx) {
                System.err.println("Error ending version access after loading user: " + endEx.getMessage());
            }
        }
    }
}