package com.spiczek.kanban.test.behaviour;

import com.spiczek.kanban.collections.User;
import com.spiczek.kanban.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Piotr Siczek
 */
@Component
public class UserBehaviour {
	private static final String TEST_USER_LOGIN = "test_user";
	private static final String TEST_USER_NAME = "name";
	private static final String TEST_USER_SURNAME = "surname";
	private static final String TEST_USER_PASSWORD = "password";

	@Autowired
	private UserRepository userRepository;

	public User isSignedIn() {
		User user = userRepository.findUserByLogin(TEST_USER_LOGIN);
		if (user == null) {
			user = new User(TEST_USER_LOGIN, TEST_USER_NAME, TEST_USER_SURNAME, TEST_USER_PASSWORD);
			userRepository.save(user);
		}

		return user;
	}
}
