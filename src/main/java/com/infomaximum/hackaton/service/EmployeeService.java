package com.infomaximum.hackaton.service;

import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.model.role.Role;
import com.infomaximum.hackaton.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    public Employee findEmployeeByLoginAndPassword(String login, String password) {
        return employeeRepository.findEmployeeByLoginAndPassword(login, password);
    }

    public boolean createEmployee(Employee employee) {
        Employee empl = employeeRepository.save(employee);
        return Objects.equals(empl.getId(), employee.getId());
    }

    @Transactional
    public void updateEmployeeRole(Employee employee, Role role) {
        employeeRepository.updateEmployeeRole(role, employee.getId());
    }

    public List<Employee> findEmployeeByCalendarEventId(Long id){
        return new ArrayList<>(employeeRepository.findEmployeeByCalendarEventsId(id));
    }
}
