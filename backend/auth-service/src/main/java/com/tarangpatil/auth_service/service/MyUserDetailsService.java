package com.tarangpatil.auth_service.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tarangpatil.auth_service.model.UserPrincipal;
import com.tarangpatil.auth_service.repository.UsersRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	private final UsersRepository repo;

	public MyUserDetailsService(UsersRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if (this.repo.existsByEmail(username))
			return new UserPrincipal(this.repo.findByEmail(username));
		throw new UsernameNotFoundException("email id " + username + " does not exist");
	}
}
