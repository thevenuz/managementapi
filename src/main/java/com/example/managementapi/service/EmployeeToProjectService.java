package com.example.managementapi.service;


import com.example.managementapi.entity.EmployeeToProjectEntity;
import com.example.managementapi.entity.ProjectEntity;
import com.example.managementapi.repository.EmployeeToProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeToProjectService {

    @Autowired
    private EmployeeToProjectRepository employeeToProjectRepository;

    public EmployeeToProjectEntity createEmployeeToProject(EmployeeToProjectEntity employeeToProject) {
        return employeeToProjectRepository.save(employeeToProject);
    }

    public List<EmployeeToProjectEntity> getAllEmployeeToProjectRelations() {
        return employeeToProjectRepository.findAll();
    }

    public Optional<EmployeeToProjectEntity> getEmployeeToProjectById(Long id) {
        return employeeToProjectRepository.findById(id);
    }

    public EmployeeToProjectEntity updateEmployeeToProject(EmployeeToProjectEntity employeeToProject) {
        return employeeToProjectRepository.save(employeeToProject);
    }

    public void deleteEmployeeToProject(Long id) {
        employeeToProjectRepository.deleteById(id);
    }

    // Method to delete employee-project associations for a given project
    public void deleteByProject(ProjectEntity project) {
        // Find and delete all EmployeeToProjectEntities associated with the given project
        List<EmployeeToProjectEntity> associations = employeeToProjectRepository.findByProject(project);
        if (associations != null && !associations.isEmpty()) {
            employeeToProjectRepository.deleteAll(associations);
        }
    }

}

