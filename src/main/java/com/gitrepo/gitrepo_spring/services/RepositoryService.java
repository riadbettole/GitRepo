package com.gitrepo.gitrepo_spring.services;

import com.gitrepo.gitrepo_spring.security.entities.User;
import com.gitrepo.gitrepo_spring.entities.Repository;
import org.springframework.data.domain.Page;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RepositoryService {
    Repository saveRepository(Repository Repository);
    Repository updateRepository(Repository Repository);
    void deleteRepository(Long id);
    void deleteAllRepository();
    Repository getRepositoryById(Long id);
    List<Repository> getAllRepository();
    Page<Repository> getAllRepositoryByPage(int page, int size);
    boolean checkIfRepoAndKeyIsValid(String userName, String repositoryName, String key);
    List<Repository> getAllRepositoryByUser(User user);
    Repository getRepositoryByNameAndUser(String repositoryName, User user);
}
