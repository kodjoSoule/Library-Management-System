package com.lms.librarymanagementsystem.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
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