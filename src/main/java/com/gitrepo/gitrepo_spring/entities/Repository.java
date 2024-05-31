package com.gitrepo.gitrepo_spring.entities;

import com.gitrepo.gitrepo_spring.security.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Repository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String language = "empty";
    private LocalDate updated;
    private boolean priv;
    private String description;
    private String secretCode;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "repository")
    @Builder.Default
    private List<Branch> branches = new ArrayList<>();

    public Repository(String name, boolean priv, String description) {
        this.name = name;
        this.updated = LocalDate.now();
        this.priv = priv;
        this.description = description;

        generateSecretCode();
    }

    public void generateSecretCode() {
        this.secretCode = UUID.randomUUID().toString();
    }
//        int length = 10; // Desired length of the random sequence
//        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
//        Random rnd = new Random();
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < length; i++) {
//            sb.append(chars[rnd.nextInt(chars.length)]);
//        }
//        System.out.println(sb);
//        this.secretCode = sb.toString();
}