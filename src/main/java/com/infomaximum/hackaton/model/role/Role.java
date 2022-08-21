package com.infomaximum.hackaton.model.role;

import com.infomaximum.hackaton.model.employee.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    Long id;

    @Column(name = "type", nullable = false)
    private RoleEnum type;

    @OneToMany(mappedBy="role")
    private Set<Employee> items;
}