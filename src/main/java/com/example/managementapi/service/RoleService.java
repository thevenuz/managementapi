//package com.example.managementapi.service;
//
//import com.example.managementapi.repository.RoleRepository;
//import org.springframework.stereotype.Service;
//import com.example.managementapi.entity.RoleEntity;
//
//import java.util.List;
//
//@Service
//public class RoleService {
//    private final RoleRepository roleRepository;
//
//    public RoleService(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    public List<RoleEntity> getAllRoles() {
//        return roleRepository.findAll();
//    }
//}