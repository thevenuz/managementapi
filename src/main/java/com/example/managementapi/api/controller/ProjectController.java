package com.example.managementapi.api.controller;

import com.example.managementapi.dto.ProjectDTO;
import com.example.managementapi.entity.ProjectEntity;
import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prm/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectEntity>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Integer id) {
        Optional<ProjectEntity> project = projectService.getProjectById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectEntity> createProject(@RequestBody ProjectDTO project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Integer id, @RequestBody ProjectDTO project) {
        try {
            return ResponseEntity.ok(projectService.updateProject(id, project));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manager/{manager}")
    public ResponseEntity<List<ProjectEntity>> getProjectsByManager(@PathVariable String manager) {
        List<ProjectEntity> projects = projectService.getProjectsByManagerUsername(manager);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/employee/{employee}")
    public ResponseEntity<List<ProjectEntity>> getProjectsByEmployee(@PathVariable String employee) {
        List<ProjectEntity> projects = projectService.getProjectsByEmployeeUsername(employee);
        return ResponseEntity.ok(projects);
    }
}
