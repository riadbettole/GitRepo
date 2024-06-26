package com.gitrepo.gitrepo_spring;

import com.gitrepo.gitrepo_spring.security.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GitrepoSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitrepoSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountService accountService) {
        return args -> {
            accountService.createUser("admin", "123", "123", "admin@gmail.com");
            accountService.createUser("manager", "123", "123", "manager@gmail.com");
            accountService.createUser("user", "123", "123", "user@gmail.com");
            accountService.createRole("ADMIN");
            accountService.createRole("MANAGER");
            accountService.createRole("USER");
            accountService.addRoleToUser("admin", "ADMIN");
            accountService.addRoleToUser("admin", "USER");
            accountService.addRoleToUser("manager", "MANAGER");
            accountService.addRoleToUser("manager", "USER");
            accountService.addRoleToUser("user", "USER");

//            System.out.println(accountService.loadUserByUsername("admin").getUsername());

        };
    }

}
