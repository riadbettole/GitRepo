package com.gitrepo.gitrepo_spring.entities;
import com.gitrepo.gitrepo_spring.enums.UserGitState;
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
public class UserGit {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private UserGitState userGitState = UserGitState.New;
    @OneToMany(mappedBy = "userGit")
    private List<Repository> repositories = new ArrayList<>();
}
