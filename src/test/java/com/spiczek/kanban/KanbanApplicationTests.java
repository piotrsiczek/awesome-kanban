package com.spiczek.kanban;

import com.spiczek.kanban.collections.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KanbanApplication.class)
public class KanbanApplicationTests {
    private static final String USER_NAME = "name";
    private static final String USER_SURNAME = "surname";
    private static final String USER_LOGIN = "login";
    private static final String USER_PASS = "pass";

    @Autowired
    private MongoTemplate mongo;

	@Test
	public void contextLoads() {
	}

    @Test
    public void createUserProfile() {
        User user = new User(USER_LOGIN, USER_NAME, USER_SURNAME, USER_PASS);
        mongo.insert(user);
    }

}
