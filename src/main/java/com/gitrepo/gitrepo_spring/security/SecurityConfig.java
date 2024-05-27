package com.gitrepo.gitrepo_spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                         authCustomiser -> authCustomiser
                                 .requestMatchers("/editUserGit","/updateUserGit","/deleteUserGit").hasRole("ADMIN")
                                 .requestMatchers("/createUserGit","/saveUserGit").hasAnyRole("ADMIN","MANAGER")
                                 .requestMatchers("/usersGitList").hasAnyRole("ADMIN","MANAGER","USER")
                                 .requestMatchers("/login","/webjars/**").permitAll()
                                 .anyRequest().authenticated()
                ).formLogin(
                    formLogin -> formLogin
                            .loginPage("/login")
                            .defaultSuccessUrl("/")
                )
                .exceptionHandling(e->e.accessDeniedPage("/accessDenied"))
        .build();
    }
    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(bCryptPasswordEncoder().encode("123")).roles("ADMIN", "USER").build(),
                User.withUsername("manager").password(bCryptPasswordEncoder().encode("123")).roles("MANAGER", "USER").build(),
                User.withUsername("user").password(bCryptPasswordEncoder().encode("123")).roles("USER").build()
        );
    }
}
