package com.spiczek.kanban.apis;

import com.spiczek.kanban.collections.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author Piotr Siczek
 */
@Component
public class BoardApi extends KanbanApi {

    @Autowired
    public BoardApi(MongoTemplate mongo) {
        super(mongo);
    }

    public Board createBoard(String ownerId, String title) {
        Board board = new Board(ownerId, title);
        mongo.insert(board);

	    mongo.updateFirst(query(where("_id").is(ownerId)), new Update().push("boardIds", board.getId()), User.class);

        return board;
    }

	public List<Board> getUserBoards(List<String> boardIds) {
		return mongo.find(query(where("_id").in(boardIds)), Board.class);
	}

    public Group createGroup(String title, String boardId, String ownerId) {
        Group group = new Group(title, Arrays.asList(), new Acl(ownerId));
        mongo.insert(group);

        mongo.updateFirst(query(where("_id").is(boardId)), new Update().push("groupIds", group.getId()), Board.class);

        return group;
    }

    public Item createItem(String text, String groupId) {
        Item item = new Item(text);
        mongo.insert(item);

        mongo.updateFirst(query(where("_id").is(groupId)), new Update().push("items", item), Group.class);

        return item;
    }

	public List<Group> getBoardDetails(List<String> groupIds) {
		return mongo.find(query(where("_id").in(groupIds)), Group.class);
	}

    public void changeItemStatus(String groupIdFrom, String groupIdTo, String itemId) {
        mongo.updateFirst(query(where("_id").is(groupIdFrom)), new Update().pull("itemIds", itemId), Group.class);
        mongo.updateFirst(query(where("_id").is(groupIdTo)), new Update().push("itemIds", itemId), Group.class);
    }

}
