package com.example.fitness.security.filter;

import com.example.fitness.core.dto.users.UserTokenDTO;
import com.example.fitness.entity.UserEntity;
import com.example.fitness.service.api.IUserService;
import com.example.fitness.security.utils.JwtTokenHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private final IUserService accountService;
	private final JwtTokenHandler jwtHandler;

	public JwtFilter(IUserService accountService, JwtTokenHandler jwtHandler) {
		this.accountService = accountService;
		this.jwtHandler = jwtHandler;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain chain)
			throws ServletException, IOException {

		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (isEmpty(header) || !header.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}

		final String token = header.split(" ")[1].trim();
		if (!jwtHandler.validate(token)) {
			chain.doFilter(request, response);
			return;
		}

		UserEntity user = accountService.getUserByMail(jwtHandler.getMail(token));
		UserTokenDTO userTokenDTO = new UserTokenDTO(user.getMail(), user.getRole().name(), user.getFio());


		UsernamePasswordAuthenticationToken
				authentication = new UsernamePasswordAuthenticationToken(
				userTokenDTO, null,
				userTokenDTO.getAuthorities());

		authentication.setDetails(
				new WebAuthenticationDetailsSource().buildDetails(request)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
}

