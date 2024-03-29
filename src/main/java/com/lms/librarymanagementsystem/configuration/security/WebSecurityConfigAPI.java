//package com.lms.librarymanagementsystem.configuration.security;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//public class WebSecurityConfigAPI extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() // Désactiver CSRF pour les API
//                .authorizeRequests()
//                .antMatchers("/api/public/**").permitAll() // Autoriser tout le monde à accéder aux endpoints publics
//                .anyRequest().authenticated() // Toutes les autres requêtes nécessitent une authentification
//                .and()
//                .formLogin().loginPage("/login").permitAll() // Gérer la page de connexion personnalisée
//                .and()
//                .logout().permitAll();
//    }
//
//}
