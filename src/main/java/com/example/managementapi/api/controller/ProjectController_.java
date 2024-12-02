//package com.example.managementapi.api.controller;
//
//import com.example.managementapi.entity.ProjectEntity;
//import com.example.managementapi.service.ProjectService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/projects")
//public class ProjectController_ {
//    private final ProjectService projectService;
//
//    public ProjectController_(ProjectService projectService) {
//        this.projectService = projectService;
//    }
//
//    @GetMapping
//    public List<ProjectEntity> getAllProjects() {
//        return projectService.getAllProjects();
//    }
//
//    @GetMapping("/{id}")
//    public ProjectEntity getProjectById(@PathVariable int id) {
//        return projectService.getProjectById(id);
//    }
//
//    @PostMapping
//    public ProjectEntity addProject(@RequestBody ProjectEntity project) {
//        return projectService.addProject(project);
//    }
//
//    @PutMapping("/{id}")
//    public ProjectEntity updateProject(@PathVariable int id, @RequestBody ProjectEntity project) {
//        return projectService.updateProject(id, project);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteProject(@PathVariable int id) {
//        projectService.deleteProject(id);
//    }
//}
//
