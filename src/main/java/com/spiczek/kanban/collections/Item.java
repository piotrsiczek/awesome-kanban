package com.spiczek.kanban.collections;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Piotr Siczek
 */
@Getter
@Document
public class Item {
    private String id;
    private String groutId;
    private String text;

    public Item(String groutId, String text) {
        this.groutId = groutId;
        this.text = text;
    }
}
