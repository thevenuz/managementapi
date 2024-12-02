//package com.example.managementapi.api.controller;
//
//import com.example.managementapi.entity.EmployeeEntity;
//import com.example.managementapi.service.EmployeeService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/employees")
//public class EmployeeController_ {
//    private final EmployeeService employeeService;
//
//    public EmployeeController_(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }
//
//
//
//    // Add a new employee
//    @PostMapping
//    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employee) {
//        return employeeService.addEmployee(employee);
//    }
//
//    // Update an employee
//    @PutMapping("/{id}")
//    public EmployeeEntity updateEmployee(@PathVariable int id, @RequestBody EmployeeEntity employee) {
//        return employeeService.updateEmployee(id, employee);
//    }
//
//    // Delete an employee
//    @DeleteMapping("/{id}")
//    public void deleteEmployee(@PathVariable int id) {
//        employeeService.deleteEmployee(id);
//    }
//
//    // Get all employees
//    @GetMapping
//    public List<EmployeeEntity> getAllEmployees() {
//        return employeeService.getAllEmployees();
//    }
//
//    // Get a single employee by ID
//    @GetMapping("/{id}")
//    public EmployeeEntity getEmployeeById(@PathVariable int id) {
//        return employeeService.getEmployeeById(id);
//    }
//}