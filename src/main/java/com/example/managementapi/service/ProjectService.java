package com.example.managementapi.service;

import com.example.managementapi.dto.ProjectDTO;
import com.example.managementapi.entity.EmployeeToProjectEntity;
import com.example.managementapi.entity.ProjectEntity;
import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.repository.EmployeeRepository;
import com.example.managementapi.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeToProjectService employeeToProjectService;

    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<ProjectEntity> getProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    // Helper method
//    public Map<String, String> getEmployeeAndRole(String username) {
//        Map<String, String> employeeDetails = new HashMap<>();
//
//        // Get the employee by username as Optional
//        Optional<EmployeeEntity> emp = employeeService.getEmployeeByUsername(username);
//
//        // If the employee is present, extract the details
//        if (emp.isPresent()) {
//            EmployeeEntity employee = emp.get();  // Unwrap the Optional to get the employee object
//            employeeDetails.put("username", employee.getUsername());  // Assuming getUsername() exists
//            employeeDetails.put("role", employee.getRole());  // Assuming getRole() exists
//        } else {
//            // Handle case where employee is not found
//            // For example, add an error message
//            employeeDetails.put("error", "Employee not found");
//        }
//
//        return employeeDetails;
//    }



//    public ProjectEntity createProject(ProjectDTO projectDTO) {
//        ProjectEntity project = new ProjectEntity();
//        project.setProjectName(projectDTO.getProject_title());
//        project.setProjectDescription(projectDTO.getProject_description());
//        project.setStartDate(projectDTO.getStart_date());
//        project.setExpectedFinishDate(projectDTO.getExpected_finish_date());
//        project.setBudget(projectDTO.getBudget());
//
//        // Fetch employee details for each user
//        //EmployeeEntity employee = employeeService.getEmployeeDetails(projectDTO.getEmployees());
//        EmployeeEntity manager = employeeService.getEmployeeDetails(projectDTO.getManager());
//        EmployeeEntity createdBy = employeeService.getEmployeeDetails(projectDTO.getCreated_by());
//
//
//
//        // Map created_by and manager to EmployeeEntity
////        EmployeeEntity createdBy = new EmployeeEntity();
////        createdBy.setEmployeeId(Integer.parseInt(projectDTO.getCreated_by()));
////        project.setCreatedBy(createdBy);
////
////        EmployeeEntity manager = new EmployeeEntity();
//        manager.setEmployeeId(Integer.parseInt(projectDTO.getManager()));
//        project.setCreatedBy(createdBy);
//        project.setManager(manager);
//        return projectRepository.save(project);
//    }



public ProjectEntity createProject(ProjectDTO projectDTO) {
    // Create a new ProjectEntity
    ProjectEntity project = new ProjectEntity();
    project.setProjectName(projectDTO.getProject_title());
    project.setProjectDescription(projectDTO.getProject_description());
    project.setStartDate(projectDTO.getStart_date());
    project.setExpectedFinishDate(projectDTO.getExpected_finish_date());
    project.setBudget(projectDTO.getBudget());
    project.setProjectStatus(ProjectEntity.ProjectStatus.in_progress);

    // Fetch employee details for created_by and manager
    EmployeeEntity manager = employeeService.getEmployeeDetails(projectDTO.getManager());
    EmployeeEntity createdBy = employeeService.getEmployeeDetails(projectDTO.getCreated_by());

    // Validate roles for manager and created_by
    if (!"manager".equalsIgnoreCase(manager.getRole().getRoleName())) {
        throw new IllegalArgumentException("The provided manager is not a manager.");
    }
    if (!"employee".equalsIgnoreCase(createdBy.getRole().getRoleName()) && !"manager".equalsIgnoreCase(createdBy.getRole().getRoleName())) {
        throw new IllegalArgumentException("The creator must have a valid role (employee or manager).");
    }

    // Set created_by and manager
    project.setCreatedBy(createdBy);
    project.setManager(manager);

    // Save project first to get its ID
    ProjectEntity savedProject = projectRepository.save(project);

    // Handle employees and map them to the project
    if (projectDTO.getEmployees() != null) {
        for (String username : projectDTO.getEmployees()) {
            EmployeeEntity employee = employeeService.getEmployeeDetails(username);
            if (!"employee".equalsIgnoreCase(employee.getRole().getRoleName())) {
                throw new IllegalArgumentException("One or more employees have invalid roles.");
            }

            // Create EmployeeToProjectEntity and save
            EmployeeToProjectEntity employeeToProject = new EmployeeToProjectEntity();
            employeeToProject.setEmployee(employee);
            employeeToProject.setProject(savedProject);

            employeeToProjectService.createEmployeeToProject(employeeToProject);
        }
    }

    return savedProject;
}


    public ProjectEntity updateProject(Integer id, ProjectEntity updatedProject) {
        return projectRepository.findById(id).map(project -> {
            project.setProjectName(updatedProject.getProjectName());
            project.setManager(updatedProject.getManager());
            project.setStartDate(updatedProject.getStartDate());
            project.setExpectedFinishDate(updatedProject.getExpectedFinishDate());
            project.setProjectStatus(updatedProject.getProjectStatus());
            return projectRepository.save(project);
        }).orElseThrow(() -> new RuntimeException("Project not found with ID: " + id));
    }

    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

    public List<ProjectEntity> getProjectsByManager(EmployeeEntity manager) {
        return projectRepository.findByManager(manager);
    }

    public List<ProjectEntity> getProjectsByEmployee(EmployeeEntity employee) {
        return projectRepository.findByCreatedBy(employee);
    }
}
