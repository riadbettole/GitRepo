package com.gitrepo.gitrepo_spring.services;

import com.gitrepo.gitrepo_spring.entities.Branch;
import com.gitrepo.gitrepo_spring.entities.Repository;
import com.gitrepo.gitrepo_spring.repositories.BranchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BranchServiceImpl implements BranchService{
    BranchRepository branchRepository;
    public Branch saveBranch(Branch branch){
        return branchRepository.save(branch);
    }
    public Branch checkIfExistOrCreate(String branchName, Repository repository){
        Branch branch = branchRepository.findByBranchNameAndRepository(branchName, repository);
        if( branch != null)
            return branch;
        return new Branch(branchName, repository);
    }
    @Override
    public List<Branch> getBranchesByRepository(Repository repository){
        return branchRepository.findByRepository(repository);
    }
}
