package com.infomaximum.hackaton.controller;

import com.infomaximum.hackaton.mapper.EmployeeMapper;
import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.model.employee.EmployeeDto;
import com.infomaximum.hackaton.model.event.EventDto;
import com.infomaximum.hackaton.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;
    @Autowired
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping("/employees")
    ResponseEntity<Boolean> newEmployee(@RequestBody EmployeeDto newEmployee) {
        boolean status = employeeService.createUser(employeeMapper.employeeDtoToEmployee(newEmployee));
        return new ResponseEntity<>(
                status,
                HttpStatus.CREATED);
    }

    @GetMapping("/employees/{id}")
    ResponseEntity<EmployeeDto> getEmployee(@PathVariable(name = "id") Long employeeId) {
        Employee employee = employeeService.findEmployeeById(employeeId);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(
                employeeMapper.employeeToEmployeeDto(employee),
                HttpStatus.FOUND);
    }

    @GetMapping("/employees/{login}/{password}")
    ResponseEntity<EmployeeDto> getEmployee(@PathVariable(name = "login") String login, @PathVariable(name = "password") String password) {
        Employee employee = employeeService.findEmployeeByLoginAndPassword(login, password);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(
                employeeMapper.employeeToEmployeeDto(employee),
                HttpStatus.FOUND);
    }
}
