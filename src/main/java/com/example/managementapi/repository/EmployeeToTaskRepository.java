package com.example.managementapi.repository;

import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.entity.EmployeeToTaskEntity;
import com.example.managementapi.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeToTaskRepository extends JpaRepository<EmployeeToTaskEntity, Integer> {
    List<EmployeeToTaskEntity> findByTask(TaskEntity task);

    List<EmployeeToTaskEntity> findByEmployee(EmployeeEntity employee);

    void deleteByTask(TaskEntity task);

    void deleteByEmployee(EmployeeEntity employee);
}

