package eams.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eams.user.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
	public boolean existsByEmail(String email);

	public Optional<Users> findByEmail(String username);
}
