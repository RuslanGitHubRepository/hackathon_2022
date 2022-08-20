package com.infomaximum.hackaton.repository;

import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.model.role.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findEmployeeById(Long id);
    Employee findEmployeeByLoginAndPassword(String login, String password);
    @Modifying@Query("update Employee e set e.role = ?1 where e.id = ?2")
    void updateEmployeeRole(Role role, Long employeeId);
}
