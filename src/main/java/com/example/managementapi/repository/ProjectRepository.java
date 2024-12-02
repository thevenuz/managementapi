package com.example.managementapi.repository;

import com.example.managementapi.entity.ProjectEntity;
import com.example.managementapi.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
    List<ProjectEntity> findByManager(EmployeeEntity manager);
    List<ProjectEntity> findByCreatedBy(EmployeeEntity employee);

    @Query("SELECT p FROM ProjectEntity p WHERE p.manager.username = :username")
    List<ProjectEntity> findProjectsByManagerUsername(@Param("username") String username);

    @Query("SELECT ep.project FROM EmployeeToProjectEntity ep WHERE ep.employee.username = :username")
    List<ProjectEntity> findProjectsByEmployeeUsername(@Param("username") String username);
}
