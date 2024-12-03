package com.example.managementapi.service;

import com.example.managementapi.dto.EmployeeDTO;
import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.entity.RoleEntity;
import com.example.managementapi.repository.EmployeeRepository;
import com.example.managementapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

//    public Optional<EmployeeEntity> getEmployeeByUsername(String username) {
//        return employeeRepository.findByUsername(username);
//    }
    // Fetch employee details by username
    public EmployeeEntity getEmployeeDetails(String username) {
        return employeeRepository.findEmployeeByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found for username: " + username));
    }

    public EmployeeEntity createEmployee(EmployeeDTO employeeDTO) {
        // Fetch role entity by role name
        RoleEntity role = roleRepository.findByRoleName(employeeDTO.getRole())
                .orElseThrow(() -> new IllegalArgumentException("Invalid role name: " + employeeDTO.getRole()));

        // Create new employee entity
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeName(employeeDTO.getName());
        employee.setUsername(employeeDTO.getUsername());
        employee.setPassword(employeeDTO.getPassword()); // Ensure proper hashing in production
        employee.setRole(role);

        // Save the employee entity
        return employeeRepository.save(employee);
    }

}
