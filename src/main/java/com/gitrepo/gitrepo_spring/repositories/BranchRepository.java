package com.gitrepo.gitrepo_spring.repositories;

import com.gitrepo.gitrepo_spring.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
}
