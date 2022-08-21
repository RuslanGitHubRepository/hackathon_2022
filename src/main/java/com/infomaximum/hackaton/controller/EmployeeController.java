package com.infomaximum.hackaton.controller;

import com.infomaximum.hackaton.mapper.EmployeeMapper;
import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.model.employee.EmployeeDto;
import com.infomaximum.hackaton.model.role.RoleDto;
import com.infomaximum.hackaton.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper, HttpHeaders httpHeaders) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping("/employees")
    ResponseEntity<Boolean> newEmployee(@RequestBody EmployeeDto newEmployee) {
        boolean status = employeeService.createEmployee(employeeMapper.employeeDtoToEmployee(newEmployee));
        return new ResponseEntity<>(
                status,
                HttpStatus.CREATED);
    }

    @GetMapping("/employees/{id}")
    ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") Long employeeId) {
        Employee employee = employeeService.findEmployeeById(employeeId);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(
                employeeMapper.employeeToEmployeeDto(employee),
                HttpStatus.OK);
    }

    @GetMapping("/employees/check")
    ResponseEntity<EmployeeDto> getEmployee(@RequestParam String login, @RequestParam String password) {
        Employee employee = employeeService.findEmployeeByLoginAndPassword(login, password);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(
                employeeMapper.employeeToEmployeeDto(employee),
                HttpStatus.OK);
    }

    @GetMapping("/employees/calendar-event/{id}")
    ResponseEntity<List<EmployeeDto>> getEmployeeByCalendarEvent(@PathVariable(name = "id") Long calendarEventId) {
        List<Employee> employees = employeeService.findEmployeeByCalendarEventId(calendarEventId);
        return new ResponseEntity<>(
                employeeMapper.listEmployeeToListEmployeeDto(employees),
                HttpStatus.OK);
    }
}
