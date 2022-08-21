package com.infomaximum.hackaton.model.employee;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class EmployeeDto implements Serializable {
    private Long id;
    private String userName;
    private String surName;
    private String login;
    private String password;
    private LocalDate birthday;
}
