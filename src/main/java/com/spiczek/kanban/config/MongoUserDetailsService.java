package com.spiczek.kanban.config;

import com.spiczek.kanban.collections.User;
import com.spiczek.kanban.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Piotr Siczek
 */
@Service
public class MongoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = userRepository.findUserByLogin(login);
		if (user == null) {
			throw new BadCredentialsException("Wrong username or password.");
		} else {
			return new AuthUser(user);
		}
	}
}
