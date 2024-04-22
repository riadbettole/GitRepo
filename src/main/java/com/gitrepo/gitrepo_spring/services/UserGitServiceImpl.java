package com.gitrepo.gitrepo_spring.services;

import com.gitrepo.gitrepo_spring.entities.UserGit;
import com.gitrepo.gitrepo_spring.repositories.UserGitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserGitServiceImpl implements UserGitService {
    private UserGitRepository userGitRepository;
    @Override
    public UserGit saveUserGit(UserGit userGit) {
        return userGitRepository.save(userGit);
    }

    @Override
    public UserGit updateUserGit(UserGit userGit) {
        return userGitRepository.save(userGit);
    }

    @Override
    public void deleteUserGit(Long id) {
        userGitRepository.deleteById(id);
    }

    @Override
    public void deleteAllUsersGit() {
        userGitRepository.deleteAll();
    }

    @Override
    public UserGit getUserGitById(Long id) {
        return userGitRepository.findById(id).get();
    }

    @Override
    public List<UserGit> getAllUsersGit() {
        return userGitRepository.findAll();
    }
}
