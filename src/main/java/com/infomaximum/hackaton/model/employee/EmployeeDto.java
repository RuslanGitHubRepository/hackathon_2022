package com.infomaximum.hackaton.model.employee;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeDto implements Serializable {
    private String userName;
    private String surName;
    private String password;
    private LocalDateTime birthday;
}
