package com.example.managementapi.repository;

import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.entity.ProjectEntity;
import com.example.managementapi.entity.RoleEntity;

import com.example.managementapi.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    List<TaskEntity> findByProject(ProjectEntity project);
    List<TaskEntity> findByAssignedTeam(EmployeeEntity team);
}
