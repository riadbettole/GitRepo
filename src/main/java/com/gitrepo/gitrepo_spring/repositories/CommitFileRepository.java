package com.gitrepo.gitrepo_spring.repositories;


import com.gitrepo.gitrepo_spring.entities.Branch;
import com.gitrepo.gitrepo_spring.entities.CommitFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitFileRepository extends JpaRepository<CommitFile, Long> {
    List<CommitFile> findByBranch(Branch branch);
}
