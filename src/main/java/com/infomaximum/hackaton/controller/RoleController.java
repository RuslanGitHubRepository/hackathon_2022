package com.infomaximum.hackaton.controller;

import com.infomaximum.hackaton.mapper.RoleMapper;
import com.infomaximum.hackaton.model.role.Role;
import com.infomaximum.hackaton.model.role.RoleDto;
import com.infomaximum.hackaton.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RoleController {
    @Autowired
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @GetMapping("/role/{id}")
    ResponseEntity<RoleDto> getRole(@PathVariable(name = "id") Long roleId) {
        Role roleById = roleService.findRoleById(roleId);
        if (roleById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                roleMapper.roleToRoleDto(roleById),
                HttpStatus.FOUND
        );
    }

    @PostMapping("/role/")
    ResponseEntity<Boolean> newRole(@RequestBody RoleDto newRole) {
        boolean status = roleService.createRole(roleMapper.roleDtoToRole(newRole));
        return new ResponseEntity<>(
                status,
                HttpStatus.CREATED
        );
    }
}
