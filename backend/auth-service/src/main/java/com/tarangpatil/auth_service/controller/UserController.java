package com.tarangpatil.auth_service.controller;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarangpatil.auth_service.model.Users;
import com.tarangpatil.auth_service.service.JwtService;
import com.tarangpatil.auth_service.service.UsersService;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/user")
public class UserController {
	private final AuthenticationManager authenticationManager;
	private final UsersService service;
	private final UserDetailsService userDetailsService;

	public UserController(UsersService service, AuthenticationManager man, UserDetailsService userDetailsService) {
		super();
		this.service = service;
		authenticationManager = man;
		this.userDetailsService = userDetailsService;
	}

	@GetMapping("/")
	public List<Users> getAll() {
		return service.getAll();
	}

	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		return service.createUser(user);
	}

	@PostMapping("/login")
	public String login(@RequestBody AuthRequest authRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
		return JwtService.createToken(userDetails.getUsername());
	}
}

@AllArgsConstructor
@Data
class AuthRequest {
	private String email;
	private String password;
}
