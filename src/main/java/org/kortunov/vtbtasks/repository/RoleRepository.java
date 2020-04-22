package org.kortunov.vtbtasks.repository;

import org.kortunov.vtbtasks.role.model.Role;

import java.util.Optional;

public interface RoleRepository extends Repository<Role, Long> {

    Optional<Role> findByName(String name);
}
