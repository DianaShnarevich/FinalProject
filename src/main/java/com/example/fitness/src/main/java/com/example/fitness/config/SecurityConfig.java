package com.example.fitness.config;

import com.example.fitness.security.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter filter) throws Exception {
		http = http.cors().and().csrf().disable();

		http = http
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and();

		http = http
				.exceptionHandling()
				.authenticationEntryPoint(
						(request, response, ex) ->
								response.setStatus(
										HttpServletResponse.SC_UNAUTHORIZED
								)
				)
				.accessDeniedHandler(
						(request, response, ex) ->
								response.setStatus(
										HttpServletResponse.SC_FORBIDDEN
								)
				)

				.and();

		http.authorizeHttpRequests(requests -> requests
				.requestMatchers("/api/v1/users/registration").permitAll()
				.requestMatchers("/api/v1/users/verification").permitAll()
				.requestMatchers("/api/v1/users/login").permitAll()
				.requestMatchers("/api/v1/users/me").hasRole("USER")
				.requestMatchers("/api/v1/users/**").hasRole("ADMIN")
				.requestMatchers("/api/v1/product/{uuid}/dt_update/{dt_update}").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST,"/api/v1/product").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET,"/api/v1/product").permitAll()
				.requestMatchers(HttpMethod.POST,"/api/v1/recipe").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET,"/api/v1/recipe").permitAll()
				.requestMatchers("/api/v1/recipe/{uuid}/dt_update/{dt_update}").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/v1/audit").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/audit").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/audit/{uuid}").permitAll()
				.anyRequest().authenticated()
		);

		http.addFilterBefore(
				filter,
				UsernamePasswordAuthenticationFilter.class
		);

		return http.build();
	}
}
