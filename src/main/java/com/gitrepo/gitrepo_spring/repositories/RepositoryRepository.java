package com.gitrepo.gitrepo_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryRepository extends JpaRepository<com.gitrepo.gitrepo_spring.entities.Repository, Long> {
}
