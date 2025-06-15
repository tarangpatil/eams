package com.tarangpatil.auth_service.service;

import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tarangpatil.auth_service.model.Users;
import com.tarangpatil.auth_service.repository.UsersRepository;

@Service
public class UsersService {

	private final UsersRepository repo;
	private final PasswordEncoder encoder;

	public UsersService(UsersRepository repo, PasswordEncoder encoder) {
		super();
		this.repo = repo;
		this.encoder = encoder;
	}

	public List<Users> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Users createUser(Users user) {
		// TODO Auto-generated method stub
		user.setPasswordHash(encoder.encode(user.getPasswordHash()));
		if (repo.existsByEmail(user.getEmail()))
			throw new RuntimeException("email id " + user.getEmail() + " already exists");
		user.setCreatedAt(new Date(System.currentTimeMillis()));
		user.setUpdatedAt(new Date(System.currentTimeMillis()));
		return repo.save(user);
	}
}
