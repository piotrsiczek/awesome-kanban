package com.spiczek.kanban.apis;

import com.spiczek.kanban.collections.Acl;
import com.spiczek.kanban.collections.Board;
import com.spiczek.kanban.collections.Group;
import com.spiczek.kanban.collections.Item;
import com.spiczek.kanban.model.GroupResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        return board;
    }

	public List<Board> getUserBoards(List<String> boardIds) {
		return mongo.find(query(where("_id").in(boardIds)), Board.class);
	}

    public Group createGroup(String title, String boardId, String ownerId) {
//        Group group = new Group(title, new Acl(ownerId));
//        mongo.insert(group);
//
//        mongo.updateFirst(query(where("_id").is(boardId)), new Update().push("groupIds", group.getId()), Board.class);
//
//        return group;
	    return null;
    }

    public Item createItem(String text, String groupId) {
//        Item item = new Item(groupId, text);
//        mongo.insert(item);
//
//        mongo.updateFirst(query(where("_id").is(groupId)), new Update().push("itemIds", item.getId()), Group.class);

//        return item;
	    return null;
    }

    public void changeItemStatus(String groupIdFrom, String groupIdTo, String itemId) {
        mongo.updateFirst(query(where("_id").is(groupIdFrom)), new Update().pull("itemIds", itemId), Group.class);
        mongo.updateFirst(query(where("_id").is(groupIdTo)), new Update().push("itemIds", itemId), Group.class);
    }

    public List<Group> getBoard(List<String> groupIds) {
        List<Group> groups = mongo.find(query(where("_id").in(groupIds)), Group.class);
//
//        List<GroupResult> result = new ArrayList<>();
//        for (Group g : groups) {
//            if (g.getItemIds() == null) {
//                result.add(new GroupResult(g.getId(), g.getTitle(), g.getAcl()));
//            } else {
//                List<Item> items = mongo.find(query(where("_id").in(g.getItemIds())), Item.class);
//                result.add(new GroupResult(g.getId(), g.getTitle(), items, g.getAcl()));
//            }
//        }
//
//        return result;

		return groups;
    }


}
