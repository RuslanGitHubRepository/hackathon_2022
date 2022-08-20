package com.infomaximum.hackaton.repository;

import com.infomaximum.hackaton.model.role.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findRoleById(Long id);
}
