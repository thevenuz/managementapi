package com.example.managementapi.repository;

import com.example.managementapi.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    Optional<EmployeeEntity> findByUsername(String username);

    @Query("SELECT e FROM EmployeeEntity e WHERE e.username = :username")
    Optional<EmployeeEntity> findEmployeeByUsername(@Param("username") String username);

}
