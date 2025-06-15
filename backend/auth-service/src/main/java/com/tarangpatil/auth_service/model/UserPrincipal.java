package com.tarangpatil.auth_service.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class UserPrincipal implements UserDetails {
	private Users user;

	public UserPrincipal(Users user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("user"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPasswordHash();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getEmail();
	}
}
