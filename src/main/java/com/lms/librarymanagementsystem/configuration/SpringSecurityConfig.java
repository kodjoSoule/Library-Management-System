//package com.lms.librarymanagementsystem.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig {
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService  ;
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(auth -> {
//            auth.requestMatchers("/admin/**").hasRole("ADMIN");
//            auth.anyRequest().permitAll();
//        })
//                .formLogin(
//                        Customizer.withDefaults()
//                )
//                .logout(logout -> logout.logoutSuccessUrl("/"))
//                .build();
//    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
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
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(users());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//}