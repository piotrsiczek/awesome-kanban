package com.spiczek.kanban.apis;

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

    public Group createGroup(String title, String boardId) {
        Group group = new Group(title);
        mongo.insert(group);

        mongo.updateFirst(query(where("_id").is(boardId)), new Update().push("groupIds", group.getId()), Board.class);

        return group;
    }

    public Item createItem(String text, String groupId) {
        Item item = new Item(groupId, text);
        mongo.insert(item);

        mongo.updateFirst(query(where("_id").is(groupId)), new Update().push("itemIds", item.getId()), Group.class);

        return item;
    }

    public List<GroupResult> getBoard(List<String> groupIds) {
        List<Group> groups = mongo.find(query(where("_id").in(groupIds)), Group.class);

        List<GroupResult> result = new ArrayList<>();
        for (Group g : groups) {
            if (g.getItemIds() == null) {
                result.add(new GroupResult(g.getId(), g.getTitle()));
            } else {
                List<Item> items = mongo.find(query(where("_id").in(g.getItemIds())), Item.class);
                result.add(new GroupResult(g.getId(), g.getTitle(), items));
            }
        }

        return result;

//        List<String> itemIds = boards.stream().map(Group::getItemIds)
//                                        .flatMap(Collection::stream)
//                                        .collect(Collectors.toList());
//
//        List<Item> items = mongo.find(query(where("_id").in(itemIds)), Item.class);
//        items.stream().
//
//        for (Group g : boards) {
//
//        }


    }
}
