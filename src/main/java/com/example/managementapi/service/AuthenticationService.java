package com.example.managementapi.service;

import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean authenticate(String username, String password) {
        EmployeeEntity employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Compare password (plaintext for simplicity; use hashing in production)
        return employee.getPassword().equals(password);
    }
}
