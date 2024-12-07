package com.example.managementapi.api.controller;


import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.service.AuthenticationService;
import com.example.managementapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/prm/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

//        if (username == null || password == null) {
//            return ResponseEntity.badRequest().body("Username and password are required.");
//        }

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("isSuccess", false, "message", "Username and password are required."));
        }

//        try {
//            boolean isAuthenticated = authenticationService.authenticate(username, password);
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("isSuccess", isAuthenticated);
//
//
//            return ResponseEntity.ok(response);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(401).body("Invalid username or password.");
//        }
        try {
            boolean isAuthenticated = authenticationService.authenticate(username, password);

            if (isAuthenticated) {
                // Determine the user's role (admin, employee, manager)
                EmployeeEntity emp = employeeService.getEmployeeDetails(username); // Implement this method in userService.
                String role = emp.getRole().getRoleName();

                // Build the response
                Map<String, Object> data = new HashMap<>();
                data.put("isAuthenticated", true);
                data.put("role", role);
                data.put("username", username);

                Map<String, Object> response = new HashMap<>();
                response.put("isSuccess", true);
                response.put("data", data);

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body(Map.of("isSuccess", false, "message", "Invalid username or password."));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Map.of("isSuccess", false, "message", "An error occurred while processing the request."));
        }
    }
}
