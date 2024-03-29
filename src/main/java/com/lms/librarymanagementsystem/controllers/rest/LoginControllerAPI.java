package com.lms.librarymanagementsystem.controllers.rest;

import com.lms.librarymanagementsystem.configuration.security.JwtTokenUtil;
import com.lms.librarymanagementsystem.configuration.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class LoginControllerAPI {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken1(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        return ResponseEntity.ok(new AuthenticationResponse("Login Successful"));
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        return ResponseEntity.ok(new String("Login Successful"+ authenticationRequest));
    }
}
