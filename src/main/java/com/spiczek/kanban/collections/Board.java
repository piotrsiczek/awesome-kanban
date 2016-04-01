package com.spiczek.kanban.collections;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Getter
@Document
public class Board {
    private String id;
    private String ownerId;
    private List<String> groupIds = new ArrayList<>();
    private String title;

    public Board(String ownerId, String title) {
        this.ownerId = ownerId;
        this.title = title;
    }
}
