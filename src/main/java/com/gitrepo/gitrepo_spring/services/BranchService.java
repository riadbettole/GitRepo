package com.gitrepo.gitrepo_spring.services;

import com.gitrepo.gitrepo_spring.entities.Branch;
import com.gitrepo.gitrepo_spring.entities.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BranchService {
    Branch saveBranch(Branch branch);
    Branch checkIfExistOrCreate(String branchName, Repository repository);
    List<Branch> getBranchesByRepository(Repository repository);
}
