package com.example.managementapi.service;

import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    public Optional<EmployeeEntity> getEmployeeByUsername(String username) {
//        return employeeRepository.findByUsername(username);
//    }
// Fetch employee details by username
public EmployeeEntity getEmployeeDetails(String username) {
    return employeeRepository.findEmployeeByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("Employee not found for username: " + username));
}
}
