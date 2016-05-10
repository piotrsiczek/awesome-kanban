package com.spiczek.kanban.collections;

import lombok.Getter;

/**
 * @author Piotr Siczek
 */
@Getter
public class Item {
    private String text;

    public Item(String text) {
        this.text = text;
    }
}
