package eams.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eams.user.model.UserPrincipal;
import eams.user.model.Users;
import eams.user.repository.UsersRepository;

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
		Users user = repo.findByEmail(username).orElse(null);
		if (user == null)
			throw new UsernameNotFoundException("email " + username + " does not exist");
		return new UserPrincipal(user);
	}
}
