package com.example.managementapi.repository;

import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    Optional<EmployeeEntity> findByUsername(String username);

    @Query("SELECT e FROM EmployeeEntity e WHERE e.username = :username")
    Optional<EmployeeEntity> findEmployeeByUsername(@Param("username") String username);

    @Query("SELECT e FROM EmployeeEntity e WHERE e.role = :role")
    List<EmployeeEntity> findByRole(@Param("role") RoleEntity role);



}
