package com.spiczek.kanban.test;

import com.spiczek.kanban.MongoConfig;
import com.spiczek.kanban.apis.BoardApi;
import com.spiczek.kanban.collections.Group;
import com.spiczek.kanban.collections.Item;
import com.spiczek.kanban.repositories.GroupRepository;
import com.spiczek.kanban.test.behaviour.UserBehaviour;
import com.spiczek.kanban.collections.Board;
import com.spiczek.kanban.collections.User;
import com.spiczek.kanban.repositories.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;

import static com.spiczek.kanban.test.behaviour.UserBehaviour.TEST_GROUP_TITLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Piotr Siczek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MongoConfig.class, TestConfig.class})
public class BoardApiTests {

	@Autowired
	private BoardApi boardApi;

	@Autowired
	private UserBehaviour whenUser;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Test
	public void createBoardTest() {
		User user = whenUser.isSignedIn();

		Board b = boardApi.createBoard(user.getId(), "title");

		User u = whenUser.isSignedIn();

		assertThat(u.getBoardIds()).isNotNull();
		assertThat(u.getBoardIds().size()).isGreaterThan(0);
		assertTrue(u.getBoardIds().stream().anyMatch(s -> s.equals(b.getId())));

		assertThat(boardRepository.findOne(b.getId())).isNotNull();
	}

	@Test
	public void getUserBoardsTest() {
		String boardId = whenUser.createdBoardWithGroupsAndItems();
		User user = whenUser.isSignedIn();

		List<Board> boards = boardApi.getUserBoards(user.getBoardIds());

		assertThat(boards).isNotNull();
		assertThat(boards.size()).isGreaterThan(0);

		Board board = boards.stream().filter(b -> b.getId().equals(boardId)).collect(Collectors.toList()).get(0);
		assertThat(board).isNotNull();
		assertThat(board.getGroupIds()).isNotNull();
		assertThat(board.getGroupIds().size()).isEqualTo(1);
	}

	@Test
	public void createGroupTest() {
		String boardId = whenUser.createdBoard();
		User user = whenUser.isSignedIn();

		Group group = boardApi.createGroup(TEST_GROUP_TITLE, boardId, user.getId());

		assertThat(group).isNotNull();
		assertThat(groupRepository.findOne(group.getId())).isNotNull();
	}

	@Test
	public void createItemTest() {
		String groupId = whenUser.createdBoardWithGroup();
		User user = whenUser.isSignedIn();

		Item item = boardApi.createItem(TEST_GROUP_TITLE, groupId);

		assertThat(item).isNotNull();

		Group g = groupRepository.findOne(groupId);
		assertThat(g).isNotNull();
		assertThat(g.getItems()).isNotNull();
		assertThat(g.getItems().size()).isEqualTo(1);
		assertThat(g.getItems().get(0).getText()).isEqualTo(TEST_GROUP_TITLE);
	}

}
