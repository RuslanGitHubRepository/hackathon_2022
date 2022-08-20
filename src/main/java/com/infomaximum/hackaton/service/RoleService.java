package com.infomaximum.hackaton.service;

import com.infomaximum.hackaton.model.role.Role;
import com.infomaximum.hackaton.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleById(Long id) {
        return roleRepository.findRoleById(id);
    }

    public boolean createRole(Role role) {
        Role saveRole = roleRepository.save(role);
        return Objects.equals(saveRole.getId(), role.getId());
    }
}
