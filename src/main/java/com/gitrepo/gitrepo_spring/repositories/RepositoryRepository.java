package com.gitrepo.gitrepo_spring.repositories;

import com.gitrepo.gitrepo_spring.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryRepository extends JpaRepository<com.gitrepo.gitrepo_spring.entities.Repository, Long> {
    com.gitrepo.gitrepo_spring.entities.Repository findByNameAndUser(String name, User user);
    List<com.gitrepo.gitrepo_spring.entities.Repository> findByUser(User user);
}
