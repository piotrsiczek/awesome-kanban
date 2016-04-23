package com.spiczek.kanban.model;

import com.spiczek.kanban.collections.Acl;
import com.spiczek.kanban.collections.Item;
import lombok.Getter;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Getter
public class GroupResult {
    private String id;
    private String title;
    private List<Item> items;
	private Acl acl;

    public GroupResult(String id, String title, List<Item> items, Acl acl) {
	    this(id, title, acl);
	    this.items = items;
    }

    public GroupResult(String id, String title, Acl acl) {
        this.id = id;
        this.title = title;
	    this.acl = acl;
    }
}
