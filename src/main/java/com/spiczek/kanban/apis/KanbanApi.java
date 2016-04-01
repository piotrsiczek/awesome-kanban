package com.spiczek.kanban.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Piotr Siczek
 */
@Component
public class KanbanApi {

    protected MongoTemplate mongo;

    @Autowired
    public KanbanApi(MongoTemplate mongo) {
        this.mongo = mongo;
    }

}
