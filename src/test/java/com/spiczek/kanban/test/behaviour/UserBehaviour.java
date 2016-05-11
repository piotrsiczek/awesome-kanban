package com.spiczek.kanban.test.behaviour;

import com.spiczek.kanban.apis.BoardApi;
import com.spiczek.kanban.collections.*;
import com.spiczek.kanban.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author Piotr Siczek
 */
@Component
public class UserBehaviour {
	public static final String TEST_USER_LOGIN = "test_user";
	public static final String TEST_USER_NAME = "name";
	public static final String TEST_USER_SURNAME = "surname";
	public static final String TEST_USER_PASSWORD = "password";
	public static final String TEST_BOARD_TITLE = "board";
	public static final String TEST_GROUP_TITLE = "group";
	public static final String TEST_ITEM_TEXT = "item text";

	@Autowired
	private MongoTemplate mongo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BoardApi boardApi;

	public User isSignedIn() {
		User user = userRepository.findUserByLogin(TEST_USER_LOGIN);
		if (user == null) {
			user = new User(TEST_USER_LOGIN, TEST_USER_NAME, TEST_USER_SURNAME, TEST_USER_PASSWORD);
			userRepository.save(user);
		}

		return user;
	}

	public String createdBoard() {
		String ownerId = isSignedIn().getId();
		Board board = new Board(ownerId, TEST_BOARD_TITLE);
		mongo.insert(board);

		mongo.updateFirst(query(where("_id").is(ownerId)), new Update().push("boardIds", board.getId()), User.class);

		return board.getId();
	}

	public String createdBoardWithGroupsAndItems() {
		String ownerId = isSignedIn().getId();
		Board board = new Board(ownerId, TEST_BOARD_TITLE);
		mongo.insert(board);

		mongo.updateFirst(query(where("_id").is(ownerId)), new Update().push("boardIds", board.getId()), User.class);

		Group group = new Group(TEST_GROUP_TITLE, Arrays.asList(new Item(TEST_ITEM_TEXT)), new Acl(ownerId));
		mongo.insert(group);

		mongo.updateFirst(query(where("_id").is(board.getId())), new Update().push("groupIds", group.getId()), Board.class);

		return board.getId();
	}

	public String createdBoardWithGroup() {
		String ownerId = isSignedIn().getId();
		Board board = new Board(ownerId, TEST_BOARD_TITLE);
		mongo.insert(board);

		mongo.updateFirst(query(where("_id").is(ownerId)), new Update().push("boardIds", board.getId()), User.class);

		Group group = new Group(TEST_GROUP_TITLE, Arrays.asList(), new Acl(ownerId));
		mongo.insert(group);

		mongo.updateFirst(query(where("_id").is(board.getId())), new Update().push("groupIds", group.getId()), Board.class);

		return group.getId();
	}
}
