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
public class CommitFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
//    private boolean isDirectory;
//    private String message;
//    private String timeAgo;
//    @ManyToOne
//    Commit commit;

    @ManyToOne
    private Branch branch;

    public CommitFile(String name, String url, Branch branch) {
        this.name = name;
//        this.message = message;
//        this.timeAgo = timeAgo;
        this.url = url;
//        this.isDirectory = isDirectory;
        this.branch =  branch;
    }
}