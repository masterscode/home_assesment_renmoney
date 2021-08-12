package com.renmoney_ha.repositories;

import com.renmoney_ha.configurations.security.ERole;
import com.renmoney_ha.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(ERole name);
}
