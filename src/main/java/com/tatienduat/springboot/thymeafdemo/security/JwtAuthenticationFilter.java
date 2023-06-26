package com.tatienduat.springboot.thymeafdemo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private final JwtTokenProvider jwtTokenProvider;
	@Autowired
	private final UserDetailsService userDetailsService;


	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}



	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.userDetailsService = userDetailsService;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		 String token = jwtTokenProvider.resolveToken(request);

		 if(token != null && jwtTokenProvider.validateToken(token)) {

			 String userName = JwtTokenProvider.extractUsername(token);
			 UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

			 UsernamePasswordAuthenticationToken userPasswordAuthenticationToken =
					 new UsernamePasswordAuthenticationToken(userName,null, userDetails.getAuthorities());

			 SecurityContextHolder.getContext().setAuthentication(userPasswordAuthenticationToken);

		 }
		 filterChain.doFilter(request, response);

	}


}
