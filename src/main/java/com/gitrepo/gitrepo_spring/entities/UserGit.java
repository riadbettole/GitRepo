package com.gitrepo.gitrepo_spring.entities;
import com.gitrepo.gitrepo_spring.enums.UserGitState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "ERROR FIRSTNAME")
    private String name;
    @NotBlank(message = "ERROR EMAIL")
    @Email
    private String email;
    private UserGitState userGitState = UserGitState.New;
    @OneToMany(mappedBy = "userGit")
    private List<Repository> repositories = new ArrayList<>();
}
