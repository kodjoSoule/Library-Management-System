package com.lms.librarymanagementsystem.configuration.security;


import com.lms.librarymanagementsystem.model.Utilisateur;

import com.lms.librarymanagementsystem.repository.UtilisateurRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByUsername(username);
        return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
    //create new user

    public void createUserIfNeeded(
            Utilisateur dbUser
    ) {
        // Check if user already exists
        if (utilisateurRepository.findByUsername("admin") != null){
            // If user does not exist, create it
            utilisateurRepository.save(dbUser);
        }
    }


    @PostConstruct
    public void createUserIfNeeded() {
        String defaultAdminUsername = "Mohamed";
        Utilisateur existingUser = utilisateurRepository.findByUsername(defaultAdminUsername);
        if (existingUser == null) {
            Utilisateur adminUser = new Utilisateur();
            adminUser.setEmail("Mohamed@gmail.com");
            adminUser.setFirstName("Mohamed");
            adminUser.setLastName("Ben");
            adminUser.setUsername(defaultAdminUsername);
            adminUser.setPassword(passwordEncoder.encode("Mohamed")); // replace "adminPassword" with the actual password
            adminUser.setRole("ADMIN");
            utilisateurRepository.save(adminUser);
        }
    }

    public void createUtlisateur(Utilisateur utilisateur){
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateur.setRole("USER");
        utilisateurRepository.save(utilisateur);
    }

    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
