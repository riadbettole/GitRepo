package com.gitrepo.gitrepo_spring.services;

import com.gitrepo.gitrepo_spring.entities.Branch;
import com.gitrepo.gitrepo_spring.entities.CommitFile;
import com.gitrepo.gitrepo_spring.entities.Repository;
import com.gitrepo.gitrepo_spring.repositories.CommitFileRepository;
import com.gitrepo.gitrepo_spring.repositories.RepositoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.commons.io.FileUtils;

@Service
public class CommitFileStorageServiceImpl implements CommitFileStorageService{
    @Autowired
    private CommitFileRepository commitFileRepository;
    private Path fileStorageLocation;

    public CommitFileStorageServiceImpl(@Value("${app.file.upload-dir}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }


    }

    @Override
    public CommitFile saveCommitFile(CommitFile commitFile) {
        return commitFileRepository.save(commitFile);
    }

    @Override
    public List<CommitFile> findAllCommitFilesOfBranch(Branch branch){
        return commitFileRepository.findByBranch(branch);
    }

    @Override
    public String saveFile(MultipartFile file, String username, String repositoryName, String branchName, String filePath) {
//        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        int lastIndexOfSlash = filePath.lastIndexOf('\\');
        String finalFileName = filePath.substring(lastIndexOfSlash + 1);

        Path targetLocation = this.fileStorageLocation.resolve(username).resolve(repositoryName).resolve(branchName);

        try {
            Path resolvedPath = resolveFilePath(targetLocation, filePath);
            Files.createDirectories(resolvedPath.getParent());
            Files.copy(file.getInputStream(), resolvedPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File saved successfully!");
            System.out.println("File saved at: " + resolvedPath);

            return  finalFileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + finalFileName + ". Please try again!", ex);
        }
    }

    private Path resolveFilePath(Path baseDir, String filePath) {
        Path resolvedPath = baseDir.resolve(filePath);
        if (Files.isDirectory(resolvedPath)) {
            throw new RuntimeException("Cannot upload a directory: " + filePath);
        }

        Path parentPath = resolvedPath.getParent();
        if (parentPath != null) {
            String folderPath = parentPath.toString().replace(baseDir.toString() + "/", "");
            Path folderDirectory = baseDir.resolve(folderPath);
            return folderDirectory.resolve(resolvedPath.getFileName());
        }

        return resolvedPath;
    }

    @Override
    public Resource getFile(String username, String imageId) {
        try {
            Path filePath = this.fileStorageLocation.resolve(username).resolve(imageId).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + imageId);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + imageId, ex);
        }
    }
}