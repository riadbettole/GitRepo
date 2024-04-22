package com.gitrepo.gitrepo_spring.repositories;

import com.gitrepo.gitrepo_spring.entities.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends JpaRepository<Commit, Long> {
}
