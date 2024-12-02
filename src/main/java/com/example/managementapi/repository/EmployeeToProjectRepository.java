package com.example.managementapi.repository;


import com.example.managementapi.entity.EmployeeToProjectEntity;
import com.example.managementapi.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeToProjectRepository extends JpaRepository<EmployeeToProjectEntity, Long> {
    // Additional query methods if needed
    List<EmployeeToProjectEntity> findByProject(ProjectEntity project);
}
