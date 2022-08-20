package com.infomaximum.hackaton.model.employee;

import com.infomaximum.hackaton.model.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String userName;
    @Column(name = "surname", nullable = false)
    private String surName;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "birth_day", nullable = false)
    private LocalDate birthday;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="role_id")
    private Role role;
}
