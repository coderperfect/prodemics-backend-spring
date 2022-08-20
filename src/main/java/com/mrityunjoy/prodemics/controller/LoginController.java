package com.mrityunjoy.prodemics.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrityunjoy.prodemics.annotation.LogAspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@RestController()
@Slf4j
@RequestMapping("/login")
public class LoginController {
	@GetMapping()
	@LogAspect
	public ResponseEntity<TokenResponse> login(HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		log.info("Sending JWT token");

		return ResponseEntity.ok(new TokenResponse(response.getHeader("jwt"), authentication.getName(),
				authentication.getAuthorities()));
	}

	@AllArgsConstructor
	@Getter
	private class TokenResponse {
		private String token;
		private String username;
		private Collection<? extends GrantedAuthority> authorities;
	}
}
