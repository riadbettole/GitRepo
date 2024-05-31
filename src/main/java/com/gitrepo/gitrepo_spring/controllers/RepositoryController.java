package com.gitrepo.gitrepo_spring.controllers;


import com.gitrepo.gitrepo_spring.entities.Branch;
import com.gitrepo.gitrepo_spring.entities.CommitFile;
import com.gitrepo.gitrepo_spring.entities.Repository;
import com.gitrepo.gitrepo_spring.security.services.AccountService;
import com.gitrepo.gitrepo_spring.services.BranchService;
import com.gitrepo.gitrepo_spring.services.CommitFileStorageService;
import com.gitrepo.gitrepo_spring.services.RepositoryService;
import com.gitrepo.gitrepo_spring.security.entities.User;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/p")
public class RepositoryController {
    private final CommitFileStorageService commitFileStorageService;
    private final BranchService branchService;
    private RepositoryService repositoryService;
    private AccountService accountService;


    @GetMapping("/{userName}")
    public String getProfile(
            @PathVariable String userName,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "visibility", required = false) String visibility,
            @RequestParam(value = "language", required = false) String language,
            @RequestParam(value = "sort", required = false, defaultValue = "name") String sort,
            Model model) {



        model.addAttribute("search", search);
        model.addAttribute("visibility", visibility);
        model.addAttribute("language", language);
        model.addAttribute("sort", sort);

        User user = accountService.loadUserByUsername(userName);

        if (user == null) {
            return "user_not_found";
        }


        List<Repository> repositories = repositoryService.getAllRepositoryByUser(user);

        List<Repository> filteredRepositories = getFilteredRepositories(search, visibility, language, sort, model, repositories);


        int start = (page - 1) * size;
        int end = Math.min(start + size, filteredRepositories.size());
        Page<Repository> repositoryPage = new PageImpl<>(filteredRepositories.subList(start, end), PageRequest.of(page - 1, size), filteredRepositories.size());

        model.addAttribute("ownerName", user.getUsername());
        model.addAttribute("repositories", repositoryPage.getContent());
        model.addAttribute("currentPage", repositoryPage.getNumber() + 1);
        model.addAttribute("totalPages", repositoryPage.getTotalPages());

        return "profile";
    }
    private static List<Repository> getFilteredRepositories(String search, String visibility, String language, String sort, Model model, List<Repository> repositories) {
        List<String> languages = repositories.stream().map(Repository::getLanguage).distinct().collect(Collectors.toList());
        model.addAttribute("languages", languages);

        List<Repository> filteredRepositories = repositories;

        if (search != null && !search.isEmpty()) {
            filteredRepositories = filteredRepositories.stream()
                    .filter(repo -> repo.getName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (visibility != null && !visibility.isEmpty()) {
            boolean isPrivate = "private".equals(visibility);
            filteredRepositories = filteredRepositories.stream()
                    .filter(repo -> repo.isPriv() == isPrivate)
                    .collect(Collectors.toList());
        }

        if (language != null && !language.isEmpty()) {
            filteredRepositories = filteredRepositories.stream()
                    .filter(repo -> repo.getLanguage().equals(language))
                    .collect(Collectors.toList());
        }

        filteredRepositories.sort((r1, r2) -> r2.getUpdated().compareTo(r1.getUpdated()));

        return filteredRepositories;
    }

    @GetMapping("/create_repository")
    public String showCreateRepositoryForm(Model model) {
        return "create_repository";
    }

    @PostMapping("/saveRepository")
    public String createRepository(@Valid Repository repository, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "create_repository";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = accountService.loadUserByUsername(username);

//        repository.isPrivate();
        System.out.println(repository.isPriv()?"yey":"no");
        repository.setUpdated(LocalDate.now());
        repository.setUser(user);
        repository.generateSecretCode();
        Repository repo = repositoryService.saveRepository(repository);

        return "redirect:/p/" + username + "/" + repo.getName();
    }


    @GetMapping("/{userName}/{repositoryName}")
    public String repositoryById(@PathVariable String userName, @PathVariable String repositoryName, ModelMap modelMap){

        User user = accountService.loadUserByUsername(userName);
        Repository repository = repositoryService.getRepositoryByNameAndUser(repositoryName, user);

        if (repository == null) {
            // Handle repository not found
            return "repository_not_found";
        }

//        List<String> branches = Arrays.asList("main", "dev", "feature-branch");
        List<Branch> branches = branchService.getBranchesByRepository(repository);

        modelMap.addAttribute("ownerName", user.getUsername());

        if(branches.isEmpty()){

            modelMap.addAttribute("repository", repository);
            modelMap.addAttribute("files", new ArrayList<CommitFile>());
            modelMap.addAttribute("branches", new ArrayList<Branch>());
            return "repository";
        }

        List<CommitFile> files = commitFileStorageService.findAllCommitFilesOfBranch(branches.getFirst());

        modelMap.addAttribute("repository", repository);
        modelMap.addAttribute("files", files);
        modelMap.addAttribute("branches", branches);
        return "repository";
    }
}