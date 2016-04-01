package com.spiczek.kanban.apis;

import com.spiczek.kanban.collections.Board;
import com.spiczek.kanban.collections.User;
import com.spiczek.kanban.model.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author Piotr Siczek
 */
@Component
public class UserApi extends KanbanApi {

    @Autowired
    public UserApi(MongoTemplate mongo) {
        super(mongo);
    }

    public UserResult loginUser() {
        User user = mongo.find(query(where("login").is("login")), User.class).get(0);
        List<Board> boards = mongo.find(query(where("_id").in(user.getBoardIds())), Board.class);

        return new UserResult(user.getId(), user.getLogin(), user.getName(), user.getSurname(), boards);
    }
}
