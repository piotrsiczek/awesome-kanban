package com.spiczek.kanban.repositories;

import com.spiczek.kanban.collections.Board;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Piotr Siczek
 */
public interface BoardRepository extends MongoRepository<Board, String> {
}
