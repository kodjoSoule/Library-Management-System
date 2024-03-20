//package com.lms.librarymanagementsystem.configuration;
//
//import com.lms.librarymanagementsystem.model.DBUser;
//import com.lms.librarymanagementsystem.repository.DBUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
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
//	@Autowired
//	private DBUserRepository dbUserRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		DBUser user = dbUserRepository.findByUsername(username);
//
//		return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
//	}
//
//	private List<GrantedAuthority> getGrantedAuthorities(String role) {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
//		return authorities;
//	}
//	//create new user
//
//	public void createUserIfNeeded(
//			DBUser dbUser
//	) {
//		// Check if user already exists
//		if (dbUserRepository.findByUsername("admin") != null){
//			// If user does not exist, create it
//			dbUserRepository.save(dbUser);
//		}
//	}
//}
