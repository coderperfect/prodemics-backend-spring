package com.mrityunjoy.prodemics.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mrityunjoy.prodemics.filter.FilterChainExceptionHandlerFilter;
import com.mrityunjoy.prodemics.filter.JwtTokenGeneratorFilter;
import com.mrityunjoy.prodemics.filter.JwtTokenValidationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	FilterChainExceptionHandlerFilter filterChainExceptionHandlerFilter;
	
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
						.requestMatchers("/login").authenticated()
						.requestMatchers(HttpMethod.GET, "/notice/**")
							.hasAnyAuthority("admin", "student")
						.requestMatchers(HttpMethod.POST, "/notice/**").hasAnyAuthority("admin")
						.requestMatchers(HttpMethod.PUT, "/notice/**").hasAnyAuthority("admin")
						.requestMatchers(HttpMethod.DELETE, "/notice/**").hasAnyAuthority("admin")
						.requestMatchers(HttpMethod.POST, "/user/**").hasAnyAuthority("admin")
						.requestMatchers("/h2-console/**").permitAll()
						.anyRequest().denyAll()
		).addFilterBefore(new JwtTokenValidationFilter(), BasicAuthenticationFilter.class)
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(filterChainExceptionHandlerFilter, JwtTokenValidationFilter.class)
		.httpBasic(Customizer.withDefaults());

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
}
