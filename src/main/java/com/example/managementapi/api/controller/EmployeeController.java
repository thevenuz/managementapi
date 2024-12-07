package com.example.managementapi.api.controller;

import com.example.managementapi.dto.EmployeeDTO;
import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/prm/users")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity createdEmployee = employeeService.createEmployee(employeeDTO);
        //return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("data", true);
        return ResponseEntity.ok(response);
    }
}
