package com.gitrepo.gitrepo_spring.controllers;

import com.gitrepo.gitrepo_spring.entities.UserGit;
import com.gitrepo.gitrepo_spring.enums.UserGitState;
import com.gitrepo.gitrepo_spring.services.UserGitService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserGitController {

    private UserGitService userGitService;
    @RequestMapping("/createUserGit")
    public String createUserGit(){
        return "CreateUserGit";
    }

    @RequestMapping("saveUserGit")
    public String saveCustomer(@Valid UserGit userGitController, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "CreateUserGit";
        }
        userGitController.setUserGitState(UserGitState.Active);
        UserGit saveUserGit = userGitService.saveUserGit(userGitController);
        return "CreateUserGit";
    }
    //    @RequestMapping("/usersGitList")
//    public String userGitList(ModelMap modelMap){
//        List<UserGit> usersGitController = userGitService.getAllUsersGit();
//        modelMap.addAttribute("userGitVue", usersGitController);
//        return "UserGitList";
//    }
    @RequestMapping("/usersGitList")
    public String userGitList(ModelMap modelMap,
                              @RequestParam(name="page", defaultValue = "0") int page,
                              @RequestParam(name="size", defaultValue = "3") int size
    ){
        Page<UserGit> usersGit = userGitService.getAllCustomersByPage(page, size);
        modelMap.addAttribute("userList", usersGit);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pages", new int[usersGit.getTotalPages()]);
        return "UserGitList";
    }

    @RequestMapping("/deleteUserGit")
    public String deleteUserGit(@RequestParam("id")Long id, ModelMap modelMap,
                                @RequestParam(name="page", defaultValue = "0") int page,
                                @RequestParam(name="size", defaultValue = "3") int size){
        userGitService.deleteUserGit(id);
        Page<UserGit> usersGit = userGitService.getAllCustomersByPage(page, size);
        modelMap.addAttribute("userList", usersGit);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pages", new int[usersGit.getTotalPages()]);
        return "UserGitList";
    }
    @RequestMapping("/editUserGit")
    public String editUserGit(@RequestParam("id") Long id, ModelMap modelMap){
        UserGit userGitController = userGitService.getUserGitById(id);
        modelMap.addAttribute("userGitView", userGitController);
        return "EditUserGit";
    }
    @RequestMapping("/updateUserGit")
    public String updateUserGit(@ModelAttribute("userGitVue") UserGit userGitController){
        userGitService.updateUserGit(userGitController);
        return createUserGit();
    }
}

