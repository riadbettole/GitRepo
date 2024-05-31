package com.gitrepo.gitrepo_spring.services;

import com.gitrepo.gitrepo_spring.security.entities.User;
import com.gitrepo.gitrepo_spring.entities.Repository;
import com.gitrepo.gitrepo_spring.repositories.RepositoryRepository;
import com.gitrepo.gitrepo_spring.security.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {
    private RepositoryRepository RepositoryRepository;
    private AccountService accountService;
    @Override
    public Repository saveRepository(Repository Repository) {
        return RepositoryRepository.save(Repository);
    }

    @Override
    public Repository updateRepository(Repository Repository) {
        return RepositoryRepository.save(Repository);
    }

    @Override
    public void deleteRepository(Long id) {
        RepositoryRepository.deleteById(id);
    }

    @Override
    public void deleteAllRepository() {
        RepositoryRepository.deleteAll();
    }

    @Override
    public Repository getRepositoryById(Long id) {
        return RepositoryRepository.findById(id).get();
    }

    public Repository getRepositoryByNameAndUser(String repositoryName, User user) {
        return RepositoryRepository.findByNameAndUser(repositoryName, user);
    }

    @Override
    public List<Repository> getAllRepositoryByUser(User user) {
        return RepositoryRepository.findByUser(user);
    }

    @Override
    public List<Repository> getAllRepository() {
        return RepositoryRepository.findAll();
    }

    @Override
    public Page<Repository> getAllRepositoryByPage(int page, int size) {
        return RepositoryRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public boolean checkIfRepoAndKeyIsValid(String userName, String repositoryName, String key){
        User user = accountService.loadUserByUsername(userName);
        Repository repository = RepositoryRepository.findByNameAndUser(repositoryName, user);
        return repository.getSecretCode().equals(key);
    }
}
