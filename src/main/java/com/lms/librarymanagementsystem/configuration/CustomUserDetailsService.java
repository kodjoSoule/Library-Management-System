//package com.lms.librarymanagementsystem.configuration;
//
//
//import com.lms.librarymanagementsystem.model.Utilisateur;
//
//import com.lms.librarymanagementsystem.repository.UtilisateurRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UtilisateurRepository utilisateurRepository ;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Utilisateur user = utilisateurRepository.findByUsername(username);
//        return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
//    }
//
//    private List<GrantedAuthority> getGrantedAuthorities(String role) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
//        return authorities;
//    }
//    //create new user
//
//    public void createUserIfNeeded(
//            Utilisateur dbUser
//    ) {
//        // Check if user already exists
//        if (utilisateurRepository.findByUsername("admin") != null){
//            // If user does not exist, create it
//            utilisateurRepository.save(dbUser);
//        }
//    }
//}
