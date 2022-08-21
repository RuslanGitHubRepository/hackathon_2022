package com.infomaximum.hackaton.model.calendarevent;

import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.model.employee.EmployeeDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CalendarEventDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private Short minNumber;
    private HashSet<EmployeeDto> employees;
}
