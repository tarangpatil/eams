package com.tarangpatil.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarangpatil.auth_service.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	public boolean existsByEmail(String email);

	public Users findByEmail(String email);
}
