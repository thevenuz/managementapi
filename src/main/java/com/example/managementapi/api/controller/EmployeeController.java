package com.example.managementapi.api.controller;

import com.example.managementapi.dto.EmployeeDTO;
import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/prm")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/users")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity createdEmployee = employeeService.createEmployee(employeeDTO);
        //return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("data", true);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/managers")
    public ResponseEntity<?> getAllManagers() {
        try {
            List<EmployeeEntity> managers = employeeService.getManagers();

            List<Map<String, Object>> managerList = new ArrayList<>();
            for (EmployeeEntity manager : managers) {
                Map<String, Object> managerDetails = new HashMap<>();
                managerDetails.put("manager_id",manager.getEmployeeId());
                managerDetails.put("username", manager.getUsername());
                managerDetails.put("password", manager.getPassword());
                managerDetails.put("role", manager.getRole().getRoleName());
                managerList.add(managerDetails);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", managerList);

            return ResponseEntity.ok(managerList);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("isSuccess", false);
            errorResponse.put("error", "Failed to retrieve managers: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get all employees.
     *
     * @return ResponseEntity containing the list of employees.
     */
    @GetMapping("/users")
    public ResponseEntity<?> getAllEmployees() {
        try {
            List<EmployeeEntity> employees = employeeService.getAllEmployees();

            List<Map<String, Object>> employeeList = new ArrayList<>();
            for (EmployeeEntity employee : employees) {
                Map<String, Object> employeeDetails = new HashMap<>();
                employeeDetails.put("employee_id", employee.getEmployeeId());
                employeeDetails.put("username", employee.getUsername());
                employeeDetails.put("employee_name", employee.getEmployeeName());
                employeeDetails.put("password", employee.getPassword());
                employeeDetails.put("role", employee.getRole().getRoleName());
                employeeList.add(employeeDetails);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", employeeList);

            return ResponseEntity.ok(employeeList);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("isSuccess", false);
            errorResponse.put("error", "Failed to retrieve employees: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
