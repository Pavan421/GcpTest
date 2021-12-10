package com.vinnotech.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.model.User;
import com.vinnotech.portal.model.UserDto;
import com.vinnotech.portal.repository.UserRepository;

@Service
public class CustomUserDetailsService {
	@Autowired
	private UserRepository userRepository;

	/*
	 * @Autowired private PasswordEncoder bcryptEncoder;
	 */

	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { User user =
	 * userRepository.findByUsername(username); if (user == null) { throw new
	 * UsernameNotFoundException("User not found with username: " + username); }
	 * return new
	 * org.springframework.security.core.userdetails.User(user.getUsername(),
	 * user.getPassword(), new ArrayList<>()); }
	 */

	public User save(UserDto user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		return userRepository.save(newUser);
	}
}