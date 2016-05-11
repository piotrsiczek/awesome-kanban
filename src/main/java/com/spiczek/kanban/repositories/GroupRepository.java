package com.spiczek.kanban.repositories;

import com.spiczek.kanban.collections.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by psiczek on 2016-05-11.
 */
public interface GroupRepository extends MongoRepository<Group, String> {
}
