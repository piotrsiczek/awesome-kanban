package com.spiczek.kanban.test;

import com.spiczek.kanban.KanbanApplication;
import com.spiczek.kanban.collections.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KanbanApplication.class)
@WebAppConfiguration
public class KanbanApplicationTests {
    private static final String USER_NAME = "name";
    private static final String USER_SURNAME = "surname";
    private static final String USER_LOGIN = "admin";
    private static final String USER_PASS = "admin";

    @Autowired
    private MongoTemplate mongo;

	@Test
	public void contextLoads() {
	}

    @Test
    @Ignore
    public void createUserProfile() {
	    User user = new User(USER_LOGIN, USER_NAME, USER_SURNAME, USER_PASS);
	    mongo.insert(user);

	    createBoardWithGroups(user.getId());
    }

	public void createBoardWithGroups(String userId) {
		Board board = new Board(userId, "stuff");
		mongo.insert(board);

		mongo.updateFirst(query(where("_id").is(userId)), new Update().push("boardIds", board.getId()), User.class);

		List<Group> groups = new ArrayList<>();
		Group group = new Group("Todo", Collections.singletonList(new Item("check stuff")), new Acl(userId));
		Group group1 = new Group("In progress", Collections.singletonList(new Item("asdf")), new Acl(userId));
		Group group2 = new Group("Done", Collections.singletonList(new Item("asdj")), new Acl(userId));
		groups.add(group);
		groups.add(group1);
		groups.add(group2);

		mongo.insert(Collections.checkedList(groups, Group.class), Group.class);

		mongo.updateFirst(query(where("_id").is(board.getId())), new Update().pushAll("groupIds", asList(group.getId(), group1.getId(), group2.getId()).toArray()), Board.class);
	}

}
