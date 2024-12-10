package com.example.managementapi.api.controller;

import com.example.managementapi.dto.ProjectDTO;
import com.example.managementapi.entity.ProjectEntity;
import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.service.EmployeeToProjectService;
import com.example.managementapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/prm")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeToProjectService employeeToProjectService;

    @GetMapping("/projects")
    public ResponseEntity<?> getAllProjects() {
        List<Map<String, Object>> projects = projectService.getAllProjects();
        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("data", projects);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Integer id) {
        try{
        Map<String, Object> project = projectService.getProjectByIdJson(id);
        Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", project);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("isSuccess", false);
            errorResponse.put("message", "An error occurred while getting the project.");
            return ResponseEntity.status(500).body(errorResponse);
        }

    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/projects/create")
    public ResponseEntity<Map<String, Object>> createProject(@RequestBody ProjectDTO project) {
//        return ResponseEntity.ok(projectService.createProject(project));
        System.out.println("Request received: " );
        try {
            // Create the project using the service
             Map<String, Object> createdProject = projectService.createProject(project);

            // Format the response
            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", createdProject);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle exceptions and return an error response
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("isSuccess", false);
            errorResponse.put("message", "An error occurred while creating the project.");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }



    @PutMapping("/projects/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Integer id, @RequestBody ProjectDTO projectDTO) {
        try {
            Map<String, Object> project= projectService.updateProject(id, projectDTO);

            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", project);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("isSuccess", false);
            errorResponse.put("message", "An error occurred while getting the project.");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer id) {
        try{
        projectService.deleteProject(id);
            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", "Project deleted successfully.");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("isSuccess", false);
            errorResponse.put("message", "An error occurred while getting the project.");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/projects/manager/{manager}")
    public ResponseEntity<?> getProjectsByManager(@PathVariable String manager) {
        try {
            List<Map<String, Object>> projects = projectService.getProjectsByManagerUsername(manager);
            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", projects);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("isSuccess", false);
            errorResponse.put("message", "An error occurred while getting the project.");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/projects/employee/{employee}")
    public ResponseEntity<?> getProjectsByEmployee(@PathVariable String employee) {
        try{
            List<Map<String, Object>> projects = projectService.getProjectsByEmployeeUsername(employee);
            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", projects);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("isSuccess", false);
            errorResponse.put("message", "An error occurred while getting the project.");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
