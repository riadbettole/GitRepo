package com.gitrepo.gitrepo_spring.security.services;

import com.gitrepo.gitrepo_spring.security.entities.Role;
import com.gitrepo.gitrepo_spring.security.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    User createUser(String username, String password, String confirmPassword, String email);
    Role createRole(String newRole);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username, String role);
    User loadUserByUsername(String username);
}
