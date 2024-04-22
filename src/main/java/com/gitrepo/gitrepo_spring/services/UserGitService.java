package com.gitrepo.gitrepo_spring.services;

import com.gitrepo.gitrepo_spring.entities.UserGit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserGitService {
    UserGit saveUserGit(UserGit userGit);
    UserGit updateUserGit(UserGit userGit);
    void deleteUserGit(Long id);
    void deleteAllUsersGit();
    UserGit getUserGitById(Long id);
    List<UserGit> getAllUsersGit();
}
