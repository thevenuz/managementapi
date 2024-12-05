package com.example.managementapi.service;

import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.entity.EmployeeToTaskEntity;
import com.example.managementapi.entity.TaskEntity;
import com.example.managementapi.repository.EmployeeToTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeToTaskService {

    private final EmployeeToTaskRepository employeeToTaskRepository;

    public EmployeeToTaskService(EmployeeToTaskRepository employeeToTaskRepository) {
        this.employeeToTaskRepository = employeeToTaskRepository;
    }

    // Create a new mapping between employee and task
    @Transactional
    public EmployeeToTaskEntity createEmployeeToTask(EmployeeToTaskEntity employeeToTask) {
        return employeeToTaskRepository.save(employeeToTask);
    }

    // Retrieve all mappings
    public List<EmployeeToTaskEntity> getAllEmployeeToTaskMappings() {
        return employeeToTaskRepository.findAll();
    }

    // Retrieve mappings by task
    public List<EmployeeToTaskEntity> getMappingsByTask(TaskEntity task) {
        return employeeToTaskRepository.findByTask(task);
    }

    // Retrieve mappings by employee
    public List<EmployeeToTaskEntity> getMappingsByEmployee(EmployeeEntity employee) {
        return employeeToTaskRepository.findByEmployee(employee);
    }

    // Retrieve a specific mapping by ID
    public Optional<EmployeeToTaskEntity> getMappingById(Integer id) {
        return employeeToTaskRepository.findById(id);
    }

    // Delete a mapping by ID
    @Transactional
    public void deleteMappingById(Integer id) {
        employeeToTaskRepository.deleteById(id);
    }

    // Delete all mappings for a given task
    @Transactional
    public void deleteMappingsByTask(TaskEntity task) {
        employeeToTaskRepository.deleteByTask(task);
    }

    // Delete all mappings for a given employee
    @Transactional
    public void deleteMappingsByEmployee(EmployeeEntity employee) {
        employeeToTaskRepository.deleteByEmployee(employee);
    }

    @Transactional
    public void deleteByTask(TaskEntity task) {
        employeeToTaskRepository.deleteByTask(task);
    }
}

