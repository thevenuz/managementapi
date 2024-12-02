//package com.example.managementapi.api.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.managementapi.entity.RoleEntity;
//import com.example.managementapi.service.RoleService;
//
//import java.util.List;
//
//@RestController
//public class RoleController_ {
//    private final RoleService roleService;
//
//    public RoleController_(RoleService roleService) {
//        this.roleService = roleService;
//    }
//
//    @GetMapping("/roles")
//    public List<RoleEntity> getAllRoles() {
//        return roleService.getAllRoles();
//    }
//}
