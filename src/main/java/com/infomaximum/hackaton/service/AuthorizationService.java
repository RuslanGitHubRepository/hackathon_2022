package com.infomaximum.hackaton.service;

import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.model.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AuthorizationService {
    private final RoleService roleService;
    private final EmployeeService employeeService;

    @Autowired
    public AuthorizationService(RoleService roleService, EmployeeService employeeService) {
        this.roleService = roleService;
        this.employeeService = employeeService;
    }
    @Transactional
    public void updateEmployee(Long employeeId, Long roleId) {
        Employee employee = employeeService.findEmployeeById(employeeId);
        if(Objects.nonNull(employee)) {
            Role role = roleService.findRoleById(roleId);
            if(Objects.nonNull(role)) {
                employeeService.updateEmployeeRole(employee, role);
            }
        }
    }
}
