package com.example.managementapi.repository;


import com.example.managementapi.entity.EmployeeToProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeToProjectRepository extends JpaRepository<EmployeeToProjectEntity, Long> {
    // Additional query methods if needed
}
