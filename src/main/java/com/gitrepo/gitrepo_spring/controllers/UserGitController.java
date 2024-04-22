package com.gitrepo.gitrepo_spring.controllers;

import com.gitrepo.gitrepo_spring.entities.UserGit;
import com.gitrepo.gitrepo_spring.enums.UserGitState;
import com.gitrepo.gitrepo_spring.services.UserGitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class UserGitController {
    private UserGitService userGitService;
    @RequestMapping("/createUserGit")
    public String createUser(){
        return "CreateUserGit";
    }
    @RequestMapping("saveUserGit")
    public String saveCustomer(@ModelAttribute("userGitVue") UserGit userGitController){
        userGitController.setUserGitState(UserGitState.Active);
        UserGit saveUserGit = userGitService.saveUserGit(userGitController);
        return "CreateUserGit";
    }
}
