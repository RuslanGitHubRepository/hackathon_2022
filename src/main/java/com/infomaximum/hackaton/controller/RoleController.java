package com.infomaximum.hackaton.controller;

import com.infomaximum.hackaton.mapper.RoleMapper;
import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.model.role.Role;
import com.infomaximum.hackaton.model.role.RoleDto;
import com.infomaximum.hackaton.service.EmployeeService;
import com.infomaximum.hackaton.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper roleMapper;
    private final EmployeeService employeeService;

    @Autowired
    public RoleController(RoleService roleService, RoleMapper roleMapper, EmployeeService employeeService, HttpHeaders httpHeaders) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
        this.employeeService = employeeService;
    }

    @GetMapping("/role/{id}")
    ResponseEntity<RoleDto> getRole(@PathVariable(name = "id") Long roleId) {
        Role roleById = roleService.findRoleById(roleId);
        if (roleById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                roleMapper.roleToRoleDto(roleById),
                HttpStatus.OK
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

    @GetMapping("/role/employee/{id}")
    ResponseEntity<RoleDto> getRoleByEmployeeId(@PathVariable(name = "id") Long employeeId) {
        Employee employee = employeeService.findEmployeeById(employeeId);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }

        return new ResponseEntity<>(
                roleMapper.roleToRoleDto(employee.getRole()),
                HttpStatus.OK);
    }
}
