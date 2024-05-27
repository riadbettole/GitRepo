package com.gitrepo.gitrepo_spring.security.repositories;

import com.gitrepo.gitrepo_spring.security.entities.Role;
import com.gitrepo.gitrepo_spring.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRole(String role);
}

