package com.example.managementapi.repository;

import com.example.managementapi.entity.ProjectEntity;
import com.example.managementapi.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
    List<ProjectEntity> findByManager(EmployeeEntity manager);
    List<ProjectEntity> findByCreatedBy(EmployeeEntity employee);
}
