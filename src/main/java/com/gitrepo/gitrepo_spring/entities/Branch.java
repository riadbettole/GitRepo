package com.gitrepo.gitrepo_spring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchName;
    @ManyToOne
    private Repository repository;
    @OneToMany(mappedBy = "branch")
    private List<CommitFile> commitFiles = new ArrayList<>();

    public Branch(String branchName, Repository repository) {
        this.branchName = branchName;
        this.repository = repository;
    }
//    private List<Commit> commits = new ArrayList<>();
}
