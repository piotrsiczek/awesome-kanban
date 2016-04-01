package com.spiczek.kanban.model;

import com.spiczek.kanban.collections.Item;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Getter
public class GroupResult {
    private String id;
    private String title;
    private List<Item> items;

    public GroupResult(String id, String title, List<Item> items) {
        this.id = id;
        this.title = title;
        this.items = items;
    }

    public GroupResult(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
