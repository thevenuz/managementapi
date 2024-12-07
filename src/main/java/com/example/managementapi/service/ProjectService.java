package com.example.managementapi.service;

import com.example.managementapi.dto.ProjectDTO;
import com.example.managementapi.entity.EmployeeToProjectEntity;
import com.example.managementapi.entity.ProjectEntity;
import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.entity.TaskEntity;
import com.example.managementapi.repository.EmployeeRepository;
import com.example.managementapi.repository.ProjectRepository;
import com.example.managementapi.repository.TaskRepository;
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
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeToProjectService employeeToProjectService;

    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

    public Map<String, Object> getProjectByIdJson(Integer id) {
        Optional<ProjectEntity> projectOptional = projectRepository.findById(id);

        ProjectEntity  project= projectOptional.get();

        List<EmployeeToProjectEntity> emp= employeeToProjectService.getEmployeesByProject(project);

        List<String> employeeNames = new ArrayList<>();
        for (EmployeeToProjectEntity e : emp) {
            if (e.getEmployee() != null && e.getEmployee().getUsername() != null) {
                employeeNames.add(e.getEmployee().getUsername());
            }
        }

        return  formatProject(project, employeeNames);
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



public Map<String, Object> createProject(ProjectDTO projectDTO) {
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
    if (!"admin".equalsIgnoreCase(createdBy.getRole().getRoleName()) && !"manager".equalsIgnoreCase(createdBy.getRole().getRoleName())) {
        throw new IllegalArgumentException("The creator must have a valid role (admin or manager).");
    }

    // Set created_by and manager
    project.setCreatedBy(createdBy);
    project.setManager(manager);

    // Save project first to get its ID
    ProjectEntity savedProject = projectRepository.save(project);

    List<String> addedEmployees = new ArrayList<>();

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
            addedEmployees.add(employeeToProject.getEmployee().getUsername());
            employeeToProject.setProject(savedProject);

            employeeToProjectService.createEmployeeToProject(employeeToProject);
        }
    }

    return formatProject(project, addedEmployees);
}

    public List<TaskEntity> getTasksByProject(ProjectEntity project) {
        return taskRepository.findByProject(project);
    }

    private Map<String, Object> formatProject(ProjectEntity project, List<String> employees) {

        List<TaskEntity> tasks = getTasksByProject(project);

        Map<String, Object> projectDetails = new HashMap<>();
        projectDetails.put("project_id", project.getProjectId());
        projectDetails.put("project_title", project.getProjectName());
        projectDetails.put("project_description", project.getProjectDescription());
        projectDetails.put("start_date", project.getStartDate() != null ? project.getStartDate().toString() : null);
        projectDetails.put("project_status", project.getProjectStatus());
        projectDetails.put("expected_finish_date", project.getExpectedFinishDate() != null ? project.getExpectedFinishDate().toString() : null);
        projectDetails.put("budget", project.getBudget() != null ? project.getBudget().toString() : null);
        projectDetails.put("created_by", project.getCreatedBy() != null ? project.getCreatedBy().getEmployeeId() : null);
        projectDetails.put("employees", employees);
        //projectDetails.put("tasks", project.getTasks() != null ? project.getTasks().stream().map(TaskEntity::getTaskId).toList() : null);
        projectDetails.put("manager", project.getManager() != null ? project.getManager().getEmployeeId() : null);

        // Build the task list
        List<Map<String, String>> taskList = new ArrayList<>();
        if (tasks != null) {
            for (TaskEntity task : tasks) {
                Map<String, String> taskMap = new HashMap<>();
                taskMap.put("task_title", task.getTaskName());
                taskMap.put("task_description", task.getTaskDescription());
                taskList.add(taskMap);
            }
        }
        projectDetails.put("tasks", taskList);

        return projectDetails;
    }


//    public ProjectEntity updateProject(Integer id, ProjectEntity updatedProject) {
//        return projectRepository.findById(id).map(project -> {
//            project.setProjectName(updatedProject.getProjectName());
//            project.setManager(updatedProject.getManager());
//            project.setStartDate(updatedProject.getStartDate());
//            project.setExpectedFinishDate(updatedProject.getExpectedFinishDate());
//            project.setProjectStatus(updatedProject.getProjectStatus());
//            return projectRepository.save(project);
//        }).orElseThrow(() -> new RuntimeException("Project not found with ID: " + id));
//    }

    public Map<String, Object> updateProject(Integer projectId, ProjectDTO projectDTO) {
        // Fetch the existing project from the database
        ProjectEntity existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));

        // Update project details
        existingProject.setProjectName(projectDTO.getProject_title());
        existingProject.setProjectDescription(projectDTO.getProject_description());
        existingProject.setStartDate(projectDTO.getStart_date());
        existingProject.setExpectedFinishDate(projectDTO.getExpected_finish_date());
        existingProject.setBudget(projectDTO.getBudget());

        // Fetch employee details for manager and created_by
        EmployeeEntity manager = employeeService.getEmployeeDetails(projectDTO.getManager());
        EmployeeEntity createdBy = employeeService.getEmployeeDetails(projectDTO.getCreated_by());

        // Validate roles for manager and created_by
        if (!"manager".equalsIgnoreCase(manager.getRole().getRoleName())) {
            throw new IllegalArgumentException("The provided manager is not a manager.");
        }
        if (!"admin".equalsIgnoreCase(createdBy.getRole().getRoleName()) && !"manager".equalsIgnoreCase(createdBy.getRole().getRoleName())) {
            throw new IllegalArgumentException("The creator must have a valid role (admin or manager).");
        }

        // Set created_by and manager
        existingProject.setCreatedBy(createdBy);
        existingProject.setManager(manager);

        List<String> addedEmployees = new ArrayList<>();

        // Update employees: Remove old employees and add new ones
        if (projectDTO.getEmployees() != null) {
            // Remove previous employees linked to the project
            employeeToProjectService.deleteByProject(existingProject);

            // Add new employees
            for (String username : projectDTO.getEmployees()) {
                addedEmployees.add(username);
                EmployeeEntity employee = employeeService.getEmployeeDetails(username);
                if (!"employee".equalsIgnoreCase(employee.getRole().getRoleName())) {
                    throw new IllegalArgumentException("One or more employees have invalid roles.");
                }

                // Create EmployeeToProjectEntity and save
                EmployeeToProjectEntity employeeToProject = new EmployeeToProjectEntity();
                employeeToProject.setEmployee(employee);
                employeeToProject.setProject(existingProject);

                employeeToProjectService.createEmployeeToProject(employeeToProject);
            }
        }

        // Save and return the updated project
        ProjectEntity project = projectRepository.save(existingProject);

        return formatProject(project, addedEmployees);


    }


    public void deleteProject(Integer projectId) {
        // First, find the project by its ID
        Optional<ProjectEntity> projectOptional = projectRepository.findById(projectId);

        if (projectOptional.isPresent()) {
            ProjectEntity project = projectOptional.get();

            // Remove all employee-project associations for the given project
            employeeToProjectService.deleteByProject(project);

            // Now, delete the project from the project table
            projectRepository.delete(project);
        } else {
            throw new IllegalArgumentException("Project with ID " + projectId + " not found.");
        }
    }

    public List<ProjectEntity> getProjectsByManager(EmployeeEntity manager) {
        return projectRepository.findByManager(manager);
    }

    public List<ProjectEntity> getProjectsByEmployee(EmployeeEntity employee) {
        return projectRepository.findByCreatedBy(employee);
    }

    public List<Map<String, Object>> getProjectsByManagerUsername(String username) {
        EmployeeEntity manager = employeeService.getEmployeeDetails(username);

        if (!"manager".equalsIgnoreCase(manager.getRole().getRoleName())) {
            throw new IllegalArgumentException("The provided username does not belong to a manager.");
        }


        List<ProjectEntity> projects = projectRepository.findProjectsByManagerUsername(username);

        List<Map<String, Object>> formattedProjects = new ArrayList<>();

        for (ProjectEntity project : projects) {

            List<EmployeeToProjectEntity> emp= employeeToProjectService.getEmployeesByProject(project);

            List<String> employeeNames = new ArrayList<>();
            for (EmployeeToProjectEntity e : emp) {
                if (e.getEmployee() != null && e.getEmployee().getUsername() != null) {
                    employeeNames.add(e.getEmployee().getUsername());
                }
            }

            Map<String, Object> formattedProject = formatProject(project, employeeNames);
            formattedProjects.add(formattedProject);
        }

        return formattedProjects;

    }

    public List<Map<String, Object>> getProjectsByEmployeeUsername(String username) {

        EmployeeEntity employee = employeeService.getEmployeeDetails(username);

        if (!"employee".equalsIgnoreCase(employee.getRole().getRoleName())) {
            throw new IllegalArgumentException("The provided username does not belong to an employee.");
        }

        List<ProjectEntity> projects = projectRepository.findProjectsByEmployeeUsername(username);

        List<Map<String, Object>> formattedProjects = new ArrayList<>();

        for (ProjectEntity project : projects) {

            List<EmployeeToProjectEntity> emp= employeeToProjectService.getEmployeesByProject(project);

            List<String> employeeNames = new ArrayList<>();
            for (EmployeeToProjectEntity e : emp) {
                if (e.getEmployee() != null && e.getEmployee().getUsername() != null) {
                    employeeNames.add(e.getEmployee().getUsername());
                }
            }

            Map<String, Object> formattedProject = formatProject(project, employeeNames);
            formattedProjects.add(formattedProject);
        }

        return formattedProjects;
    }
}
