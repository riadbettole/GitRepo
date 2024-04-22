package com.gitrepo.gitrepo_spring.repositories;

import com.gitrepo.gitrepo_spring.entities.UserGit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGitRepository extends JpaRepository<UserGit, Long> {
}
