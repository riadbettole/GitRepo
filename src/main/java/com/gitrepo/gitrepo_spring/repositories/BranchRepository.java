package com.gitrepo.gitrepo_spring.repositories;

import com.gitrepo.gitrepo_spring.entities.Branch;
import com.gitrepo.gitrepo_spring.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    Branch findByBranchName(String branchName);
    Branch findByBranchNameAndRepository(String branchName, com.gitrepo.gitrepo_spring.entities.Repository repository);

    List<Branch> findByRepository(com.gitrepo.gitrepo_spring.entities.Repository repository);
}
