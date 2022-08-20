package com.mrityunjoy.prodemics.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mrityunjoy.prodemics.model.EndUser;
import com.mrityunjoy.prodemics.model.Role;
import com.mrityunjoy.prodemics.repository.EndUserRepository;

@Component
public class UsernameAndPasswordAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	EndUserRepository endUserRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		EndUser endUser = endUserRepository.getByUsername(username)
				.orElseThrow(() -> new BadCredentialsException("User not found"));

		if (!passwordEncoder.matches(password, endUser.getPassword()))
			throw new BadCredentialsException("Password is incorrect");

		return new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities(endUser.getRoles()));
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<Role> roles) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		
		roles.forEach((role) -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		});

		return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}

}
