package com.infomaximum.hackaton.model.employee;

import com.infomaximum.hackaton.model.calendarevent.CalendarEvent;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;
    @Column(name = "name")
    private String userName;
    @Column(name = "surname")
    private String surName;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "birth_day")
    private LocalDate birthday;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="role_id")
    private Role role;
    @ManyToMany(mappedBy = "employees")
    Set<CalendarEvent> calendarEvents;
}