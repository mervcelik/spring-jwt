package com.example.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	public static final String SECRET_KEY = "5N+6yAw9UJ1ZGIE3ivXxkQlxnb9BauSkvcdSJ447DQE=";

	public String generateToken(UserDetails userDetails) {
	    Map<String, String> claimsMap = new HashMap<>();
	    claimsMap.put("role", "ADMIN");

	    return Jwts.builder()
	            .setClaims(claimsMap)
	            .setSubject(userDetails.getUsername())
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
	            .signWith(getKey(), SignatureAlgorithm.HS256)
	            .compact();
	}
	public String getUserNameByToken(String token) {
	    return exportToken(token, Claims::getSubject);
	}

	public <T> T exportToken(String token, Function<Claims, T> claimsResolver) {
	    Claims claims = getClaims(token);
	    return claimsResolver.apply(claims);
	}

	private Claims getClaims(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(getKey())
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	}

	public Object getClaimsByKey(String token,String key) {
		Claims claims= getClaims(token);
		return claims.get(key);
	}
	public boolean isTokenExpired(String token) {
		Date expiredDate = exportToken(token, Claims::getExpiration);
		return new Date().before(expiredDate);
	}

	public Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
