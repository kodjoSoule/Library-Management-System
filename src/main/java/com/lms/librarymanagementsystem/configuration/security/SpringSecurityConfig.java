package com.lms.librarymanagementsystem.configuration.security;

import com.lms.librarymanagementsystem.model.Utilisateur;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService  ;
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/login").permitAll();
                    auth.requestMatchers("/admin/**").hasRole("ADMIN");
                    auth.requestMatchers("/user/**").hasRole("USER");
                    auth.anyRequest().permitAll();
                })
                .formLogin(
                        Customizer.withDefaults()
                )
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(accessDeniedHandler))
                ;

        // Désactiver le formulaire de connexion par défaut pour les requêtes API
        http.formLogin().disable();
        // Ajouter le filtre JWT avant le filtre d'authentification par session utilisateur
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


//    @Bean
//    public UserDetailsService users() {
//        // créer un utilisateur "user" et un utilisateur "admin"
//        UserDetails user = User
//                .builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                // ajouter un rôle à l'utilisateur "user"
//                .roles("USER").build();
//        // créer un utilisateur "admin" et un utilisateur "admin"
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                // ajouter un rôle à l'utilisateur "user" et "admin"
//                .roles("USER", "ADMIN").build();
//        // retourner une liste d'utilisateurs
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(users());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
//    @PostConstruct
//    public void createAdminUserIfNeeded() {
//        customUserDetailsService.createUserIfNeeded(
//                new Utilisateur(
//                        "Mohamed",
//                        "Ben",
//                        "Moahamed@gmail.com",
//                        "mohamed",
//                        passwordEncoder.encode("mohamed")
//                )
//        );
//    }


}