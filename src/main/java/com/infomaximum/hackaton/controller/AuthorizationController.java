package com.infomaximum.hackaton.controller;

import com.infomaximum.hackaton.service.AuthorizationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    ResponseEntity<Object> setEmployeesRole(@RequestParam Map<String, String> params) {
        Long employeeId = Long.valueOf(StringUtils.defaultString(params.get("employeeId"), "0"));
        Long roleId = Long.valueOf(params.get("roleId"));
        authorizationService.updateEmployee(employeeId, roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
