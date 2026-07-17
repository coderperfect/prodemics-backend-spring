package com.mrityunjoy.prodemics.config;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(session -> session.sessionCreationPolicy(
						SessionCreationPolicy.STATELESS))
		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
		.csrf(AbstractHttpConfigurer::disable)
		.headers(headers -> headers.frameOptions(
                HeadersConfigurer.FrameOptionsConfig::sameOrigin))
		.authorizeHttpRequests(
				authZ -> authZ
						.requestMatchers("/api/auth/login").permitAll()
						.requestMatchers(HttpMethod.GET, "/notice/**")
							.hasAnyAuthority("SCOPE_admin", "SCOPE_student")
						.requestMatchers(HttpMethod.POST, "/notice/**").hasAnyAuthority("SCOPE_admin")
						.requestMatchers(HttpMethod.PUT, "/notice/**").hasAnyAuthority("SCOPE_admin")
						.requestMatchers(HttpMethod.DELETE, "/notice/**").hasAnyAuthority("SCOPE_admin")
						.requestMatchers(HttpMethod.POST, "/user/**").hasAnyAuthority("SCOPE_admin")
						.requestMatchers("/h2-console/**").permitAll()
						.anyRequest().denyAll()
		).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

		return http.build();
	}

	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setAllowedMethods(Collections.singletonList("*"));
		corsConfig.setAllowedHeaders(Collections.singletonList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
    JwtDecoder jwtDecoder() {
		final String SECRET = "abcdreetretesdfesresdtrgfdtrdjyfdytftyyfytjftyf";
		SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

		return NimbusJwtDecoder.withSecretKey(key).build();
	}
}
