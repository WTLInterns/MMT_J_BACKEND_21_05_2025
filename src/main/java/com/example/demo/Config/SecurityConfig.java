package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.JwtConfig.JwtAuthFilter;
import com.example.demo.Service.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> request
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/masterAdmin/**").hasAnyAuthority("MASTER_ADMIN")
				.requestMatchers("/vendor/**").hasAnyAuthority("VENDOR")
				.requestMatchers("/subAdmin/**").hasAnyAuthority("SUB_ADMIN")
				.requestMatchers("/driver/**").hasAnyAuthority("DRIVER")

				.anyRequest().authenticated());
		http.csrf(csrf -> csrf.disable());
		http.csrf(AbstractHttpConfigurer::disable);
		http.cors(Customizer.withDefaults());
		http.authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthFilter,
				UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvideer = new DaoAuthenticationProvider();
		daoAuthenticationProvideer.setUserDetailsService(userDetailsServiceImpl);
		daoAuthenticationProvideer.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvideer;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}