package com.gitrepo.gitrepo_spring.security.services;

import com.gitrepo.gitrepo_spring.security.entities.Role;
import com.gitrepo.gitrepo_spring.security.entities.User;
import com.gitrepo.gitrepo_spring.security.repositories.RoleRepository;
import com.gitrepo.gitrepo_spring.security.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(String username, String password, String confirmPassword, String email) {
        User user1 = userRepository.findByUsername(username);
        if (user1 != null) {
            throw new RuntimeException("Username already exists");
        }
        if(!password.equals(confirmPassword)) {
            throw new RuntimeException("Passwords do not match");
        }
        user1 = User.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .build();
        return userRepository.save(user1);
    }

    @Override
    public Role createRole(String newRole) {
        Role role1 = roleRepository.findByRole(newRole);
        if(role1 != null) { throw new RuntimeException("Role does not exist"); }
        role1 = Role.builder()
                .role(newRole)
                .build();
        return roleRepository.save(role1);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        User user1 = userRepository.findByUsername(username);
        Role role1 = roleRepository.findByRole(role);
        user1.getRoles().add(role1);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        User user1 = userRepository.findByUsername(username);
        Role role1 = roleRepository.findByRole(role);
        user1.getRoles().remove(role1);
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
