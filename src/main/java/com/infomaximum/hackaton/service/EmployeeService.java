package com.infomaximum.hackaton.service;

import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean createUser(Employee employee) {
        Employee empl = employeeRepository.save(employee);
        return empl.getId() == employee.getId();
    }
}
