package com.infomaximum.hackaton.controller;

import com.infomaximum.hackaton.service.AuthorizationService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.tuple.CreationTimestampGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AuthorizationController {
    private AuthorizationService authorizationService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PutMapping("/employees/role")
    ResponseEntity<Object> setEmployeesRole(@RequestParam String emp_id, @RequestParam String role_id) {
        long employeeId = Long.valueOf(StringUtils.defaultString(emp_id, "0"));
        long roleId = Long.valueOf(StringUtils.defaultString(role_id, "0"));
        if(employeeId != 0 && roleId != 0) {
            authorizationService.updateEmployee(employeeId, roleId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
