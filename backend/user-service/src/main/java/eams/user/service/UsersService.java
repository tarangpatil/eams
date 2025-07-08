package eams.user.service;

import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eams.user.dto.UsersDTO;
import eams.user.model.Users;
import eams.user.repository.UsersRepository;

@Service
public class UsersService {
	private final UsersRepository repo;
	private final PasswordEncoder passwordEncoder;

	public UsersService(UsersRepository repo, PasswordEncoder enc) {
		super();
		this.repo = repo;
		passwordEncoder = enc;
		this.repo.deleteAll();
		System.out.println(this.repo.findAll());
	}

	public UsersDTO createUser(Users user) {
		user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		return new UsersDTO(repo.save(user));
	}

	public UsersDTO getById(int id) {
		return new UsersDTO(repo.findById(id).orElse(null));
	}
}
