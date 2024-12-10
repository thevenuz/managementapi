package com.example.managementapi.service;

import com.example.managementapi.dto.EmployeeDTO;
import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.entity.RoleEntity;
import com.example.managementapi.repository.EmployeeRepository;
import com.example.managementapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        System.out.println("hit 22" + username);
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

//    public List<EmployeeEntity> getEmployeesByRole(String role) {
//        return roleRepository.findByRoleName(role);
//    }

    public List<EmployeeEntity> getManagers() {
        // Find the "manager" role
        Optional<RoleEntity> role = roleRepository.findByRoleName("manager");

        if (role.isEmpty()) {
            throw new IllegalArgumentException("Role 'manager' not found.");
        }

        // Find employees with the "manager" role
        return employeeRepository.findByRole(role.get());
    }

    /**
     * Get all employees.
     *
     * @return List of all employees.
     */
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

}
