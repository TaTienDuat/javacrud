package com.tatienduat.springboot.thymeafdemo.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
@Configuration
public class JwtTokenProvider {

	private static final String JWT_SECRET = "test123";

	private final static Long JWT_EXPIRATION = 604800000L;

	// Create token web form user
	public static String generateToken(UserDetails userDetails) {

		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + JWT_EXPIRATION);

		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(now)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
				.compact();
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        return authentication.getAuthorities();
    }



	// Get information user from JWT

	public static  String extractUsername(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public static boolean isTokenExpired(String token) {
		Date expirationDate = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getExpiration();

		return expirationDate.before(new Date());
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String resolveToken(HttpServletRequest request) {

		String bearerToken = request.getHeader("Authorization");

		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7); // Remove "Bearer " prefix
		}

		return null;
	}

}
