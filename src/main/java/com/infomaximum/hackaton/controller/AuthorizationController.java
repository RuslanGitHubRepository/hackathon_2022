package com.infomaximum.hackaton.controller;

import com.infomaximum.hackaton.service.AuthorizationService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.tuple.CreationTimestampGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController {
    private AuthorizationService authorizationService;
    private HttpHeaders httpHeaders;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService, HttpHeaders httpHeaders) {
        this.authorizationService = authorizationService;
        this.httpHeaders = httpHeaders;
    }

    @PutMapping("/employees/role")
    ResponseEntity<Object> setEmployeesRole(@RequestParam String emp_id, @RequestParam String role_id) {
        long employeeId = Long.valueOf(StringUtils.defaultString(emp_id, "0"));
        long roleId = Long.valueOf(StringUtils.defaultString(role_id, "0"));
        if(employeeId != 0 && roleId != 0) {
            authorizationService.updateEmployee(employeeId, roleId);
            return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
    }
}
