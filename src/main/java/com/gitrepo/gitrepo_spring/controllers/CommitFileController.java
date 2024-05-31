package com.gitrepo.gitrepo_spring.controllers;

import com.gitrepo.gitrepo_spring.entities.Branch;
import com.gitrepo.gitrepo_spring.entities.CommitFile;
import com.gitrepo.gitrepo_spring.entities.Repository;
import com.gitrepo.gitrepo_spring.security.services.AccountService;
import com.gitrepo.gitrepo_spring.services.BranchService;
import com.gitrepo.gitrepo_spring.services.CommitFileStorageService;
import com.gitrepo.gitrepo_spring.services.RepositoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import com.gitrepo.gitrepo_spring.security.entities.User;

import java.net.URI;
import java.net.URL;

@RestController
@RequestMapping("/upload")
@AllArgsConstructor
public class CommitFileController {
    private AccountService accountService;
    private RepositoryService repositoryService;
    private CommitFileStorageService commitFileStorageService;
    private BranchService branchService;

    @PostMapping("/")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam ("url") String url,
                                        @RequestParam("filePath") String filePath,
                                        @RequestParam("branchName") String branchName,
                                        @RequestParam("key") String key
                                        ) {
        String userName = "";
        String repositoryName = "";

        try {
            URL parsedUrl = new URI(url).toURL();

            String host = parsedUrl.getHost();
            String path = parsedUrl.getPath();

            if (host.equals("localhost")) {
                String[] parts = path.split("/");
                userName = parts[2];
                repositoryName = parts[3];
            }else {
                return ResponseEntity.badRequest().body("Invalid URL host: " + host);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error parsing URL: " + e.getMessage());
        }

        if(repositoryService.checkIfRepoAndKeyIsValid(userName, repositoryName, key)){
            String name = commitFileStorageService.saveFile(file, userName, repositoryName, branchName, filePath);

            User user = accountService.loadUserByUsername(userName);
            Repository repository = repositoryService.getRepositoryByNameAndUser(repositoryName, user);
            Branch branch = branchService.checkIfExistOrCreate(branchName, repository);

            CommitFile commitFile = new CommitFile(name,filePath,branch);

            branchService.saveBranch(branch);
            commitFileStorageService.saveCommitFile(commitFile);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().body("Repository or key is invalid!");
    }

    @GetMapping("/{username}/{fileId}")
    public ResponseEntity<?> getFile(@PathVariable("username") String username,
                                     @PathVariable("fileId") String fileId) {
        Resource file = commitFileStorageService.getFile(username, fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

//    private String generateUniqueImageId() {
//        return UUID.randomUUID().toString();
//    }
}
