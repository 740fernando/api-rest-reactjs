package br.com.everis.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.everis.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final String NOT_FOUND = " not found";
	private static final String USER_NAME = "Username ";
	
	private UserRepository repository;
	
	public UserDetailsServiceImpl(UserRepository repository) {
		this.repository = repository;	
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username);	
		return user.orElseThrow(()-> new UsernameNotFoundException(USER_NAME.concat(username).concat(NOT_FOUND)));
	}

}
