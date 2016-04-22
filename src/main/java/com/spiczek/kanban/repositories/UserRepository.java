package com.spiczek.kanban.repositories;

import com.spiczek.kanban.collections.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Piotr Siczek
 */
public interface UserRepository extends MongoRepository<User, String> {
	User findUserByLogin(String login);
}
