package com.nitheesh.bloggingplatform.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private String SECERT_KEY = "thisisaverysecretkeyabcdeqwrtddugfcdt6dugvhoiuhjbjblh";
    private int EXPIRATION_IN_MS = 1000*60*60*24;

    public String generateToken(String email,String role){
        return Jwts.builder()
                .setSubject(email)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_IN_MS))
                .signWith(Keys.hmacShaKeyFor(SECERT_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getALlClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECERT_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmail(String token){
        return getALlClaims(token).getSubject();
    }

    public String getRole(String token){
        return getALlClaims(token).get("role",String.class);
    }

    public boolean IsTokenValid(String token){
        try{
            getALlClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
