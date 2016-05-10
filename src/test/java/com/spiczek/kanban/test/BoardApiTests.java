package com.spiczek.kanban.test;

import com.spiczek.kanban.MongoConfig;
import com.spiczek.kanban.apis.BoardApi;
import com.spiczek.kanban.test.behaviour.UserBehaviour;
import com.spiczek.kanban.collections.Board;
import com.spiczek.kanban.collections.User;
import com.spiczek.kanban.repositories.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Piotr Siczek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:mongo.properties"})
@SpringApplicationConfiguration(classes = {TestConfig.class, MongoConfig.class})
//@WebAppConfiguration
@ComponentScan(basePackages = {"com.spiczek.kanban.test.behaviour"})
public class BoardApiTests {

	@Autowired
	private BoardApi boardApi;

	@Autowired
	private UserBehaviour whenUser;

	@Autowired
	private BoardRepository boardRepository;

	private User user;

	@Test
	public void createBoardTest() {
		user = whenUser.isSignedIn();

		Board b = boardApi.createBoard(user.getId(), "title");

		User u = whenUser.isSignedIn();

		assertThat(u.getBoardIds()).isNotNull();
		assertThat(u.getBoardIds().size()).isGreaterThan(0);
		assertTrue(u.getBoardIds().stream().anyMatch(s -> s.equals(b.getId())));

		assertThat(boardRepository.findOne(b.getId())).isNotNull();
	}
}
