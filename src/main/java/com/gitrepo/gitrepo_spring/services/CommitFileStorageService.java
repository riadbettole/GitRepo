package com.gitrepo.gitrepo_spring.services;

import com.gitrepo.gitrepo_spring.entities.Branch;
import com.gitrepo.gitrepo_spring.entities.CommitFile;
import com.gitrepo.gitrepo_spring.entities.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface CommitFileStorageService {
    public String saveFile(MultipartFile file, String username, String repositoryName, String branchName, String filePath);
    public Resource getFile(String username, String imageId);
    public CommitFile saveCommitFile(CommitFile commitFile);
    public List<CommitFile> findAllCommitFilesOfBranch(Branch branch);
}
