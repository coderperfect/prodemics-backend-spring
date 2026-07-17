package com.mrityunjoy.prodemics.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService {
    public String generateToken(
            Authentication auth) {

        if (auth == null) return null;

        SecretKey key = Keys
                .hmacShaKeyFor("abcdreetretesdfesresdtrgfdtrdjyfdytftyyfytjftyf".getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder().setIssuer("prodemics").setSubject("JWT Token")
                .claim("username", auth.getName())
                .claim("scope", populateAuthorities(auth.getAuthorities())).setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (3600 * 1000))).signWith(key).compact();

        return jwt;
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(" ", authoritiesSet);
    }
}
