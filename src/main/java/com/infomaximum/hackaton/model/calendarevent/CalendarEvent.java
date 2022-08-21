package com.infomaximum.hackaton.model.calendarevent;

import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.model.comment.Comment;
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
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "calendar_event")
@Getter
@Setter
public class CalendarEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ce_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "min_number")
    private Short minNumber;
    @OneToMany(mappedBy="calendarEvent")
    private Set<Comment> comments;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "calendar_event_employee",
            joinColumns = @JoinColumn(name = "ce_id"),
            inverseJoinColumns = @JoinColumn(name = "emp_id"))
    Set<Employee> employees;
}
