package com.tarangpatil.auth_service.service;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String token = "fptKiuZmsdD0kxWMIwYOn1bH2uDVtLkWMFstL0T59UjugI75B1/KRrvBSRFjts4v6PC8fHgDc3ZOchhGQQm9SQ==";
	private static final long VALIDITY_MS = 1000 * 60 * 60 * 24;

	public static String createToken(String username) {
		return Jwts//
				.builder()//
				.subject(username)//
				.issuedAt(new Date(System.currentTimeMillis()))//
				.expiration(new Date(System.currentTimeMillis() + VALIDITY_MS))//
				.signWith(getKey())//
				.compact();
	}

	private static Claims extractAllClaims(String token) {
		return (Claims) Jwts//
				.parser()//
				.verifyWith(getKey())//
				.build()//
				.parse(token)//
				.getPayload();
	}

	public static boolean validateToken(String token, String username) {
		Claims claims = extractAllClaims(token);
		return extractAllClaims(token).getExpiration().after(new Date()) && username.equals(claims.getSubject());
	}

	private static SecretKey getKey() {
		// TODO Auto-generated method stub
		return Keys.hmacShaKeyFor(Base64.getDecoder().decode(token));
	}

	public static String extractUsername(String jwtToken) {
		// TODO Auto-generated method stub
		return extractAllClaims(jwtToken).getSubject();
	}
}
