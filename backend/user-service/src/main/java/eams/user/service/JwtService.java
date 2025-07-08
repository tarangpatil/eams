package eams.user.service;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import eams.user.dto.UsersDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtService {
	private static final String SECRET_KEY = System.getenv("SECRET_KEY");
	private static final long VALIDITY = Long.parseLong(System.getenv("VALIDITY"));

	public static String createToken(UsersDTO user) {
		return Jwts//
				.builder()//
				.subject(user.getEmail())//
				.issuedAt(new Date())//
				.expiration(new Date(System.currentTimeMillis() + VALIDITY))//
				.signWith(getKey())//
				.compact();
	}

	public static boolean validateToken(String token) {
		return !isExpired(token);
	}

	private static boolean isExpired(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token).getExpiration().before(new Date());
	}

	public static Claims extractClaims(String token) {
		// TODO Auto-generated method stub
		return (Claims) Jwts//
				.parser()//
				.verifyWith(getKey())//
				.build()//
				.parse(token)//
				.getPayload();
	}

	private static SecretKey getKey() {
		// TODO Auto-generated method stub
		return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
	}
}
