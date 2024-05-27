package com.gitrepo.gitrepo_spring.security.repositories;

import com.gitrepo.gitrepo_spring.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
