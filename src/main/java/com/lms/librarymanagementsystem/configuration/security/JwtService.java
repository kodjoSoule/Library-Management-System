//package com.lms.librarymanagementsystem.configuration.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtService {
//
//    public static final String SECRET_KEY = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";
//    private long validityInMilliseconds = 3600000; // 1h
//    private static final long EXPIRATION_TIME = 900_000; // 15 minutes
//
//    public static  String generateToken(String  username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
//    }
//
//    public static String validateToken(String token) {
//
//
//        Claims claims = Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//
//}