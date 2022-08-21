package com.infomaximum.hackaton.mapper;

import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.model.employee.EmployeeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);
    EmployeeDto employeeToEmployeeDto(Employee employee);
    List<EmployeeDto> listEmployeeToListEmployeeDto(List<Employee> employee);
}
