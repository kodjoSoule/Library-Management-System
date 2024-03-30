package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.configuration.security.CustomUserDetailsService;
import com.lms.librarymanagementsystem.model.Utilisateur;
import com.lms.librarymanagementsystem.service.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
public class LoginController {
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	//spring post authentification for login
	@PostMapping("/login")
	public String login(@ModelAttribute Utilisateur user, RedirectAttributes redirectAttributes) {
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Invalid username or password");
			return "redirect:/login";
		}
		return "redirect:/";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
	@GetMapping("/register")
	public String register(Model model) {

		Utilisateur user = new Utilisateur();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute Utilisateur user) {
		log.info("User: {}", user);
		customUserDetailsService.createUtlisateur(user);
		return "redirect:/login";
	}

//	@GetMapping("/*")
//	public String getUserInfo(Principal user) {
//		StringBuffer userInfo= new StringBuffer();
//		if(user instanceof UsernamePasswordAuthenticationToken){
//			userInfo.append(getUsernamePasswordLoginInfo(user));
//		} else if(user instanceof OAuth2AuthenticationToken){
//			userInfo.append(getOauth2LoginInfo(user));
//		}
//		return userInfo.toString();
//	}

}