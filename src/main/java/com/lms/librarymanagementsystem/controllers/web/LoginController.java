//package com.lms.librarymanagementsystem.controllers;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.Principal;
//
//@RestController
//
//public class LoginController {
//	private final  OAuth2AuthorizedClientService  authorizedClientService;
//
//	public LoginController(OAuth2AuthorizedClientService authorizedClientService) {
//		this.authorizedClientService = authorizedClientService;
//	}
//	private StringBuffer getOauth2LoginInfo(Principal user){
//		StringBuffer protectedInfo = new StringBuffer();
//		OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
//		OAuth2AuthorizedClient authClient = this.authorizedClientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());
//		return protectedInfo;
//	}
//
//
//	@GetMapping("/login")
//	public String login() {
//		return "login";
//	}
//
//	@GetMapping("/user")
//	public String getUser() {
//		return "Welcome, User";
//	}
//
//	@GetMapping("/admin")
//	public String getAdmin() {
//		return "Welcome, Admin";
//	}
//
////	@GetMapping("/*")
////	public String getUserInfo(Principal user) {
////		StringBuffer userInfo= new StringBuffer();
////		if(user instanceof UsernamePasswordAuthenticationToken){
////			userInfo.append(getUsernamePasswordLoginInfo(user));
////		} else if(user instanceof OAuth2AuthenticationToken){
////			userInfo.append(getOauth2LoginInfo(user));
////		}
////		return userInfo.toString();
////	}
//	private StringBuffer getUsernamePasswordLoginInfo(Principal user)
//	{
//		StringBuffer usernameInfo = new StringBuffer();
//
//		UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
//		if(token.isAuthenticated()){
//			User u = (User) token.getPrincipal();
//			usernameInfo.append("Welcome, " + u.getUsername());
//		}
//		else{
//			usernameInfo.append("NA");
//		}
//		return usernameInfo;
//	}
//
//}