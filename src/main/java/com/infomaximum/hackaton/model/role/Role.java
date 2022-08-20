package com.infomaximum.hackaton.model.role;

import com.infomaximum.hackaton.model.employee.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    Long id;

    @Column(name = "type", nullable = false)
    private RoleEnum role;

    @OneToMany(mappedBy="role")
    private Set<Employee> items;
}