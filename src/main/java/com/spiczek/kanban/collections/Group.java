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
public class Group {
    private String id;
    private String title;
    private List<Item> items  = new ArrayList<>();
	private Acl acl;

    public Group(String title, List<Item> items, Acl acl) {
        this.title = title;
	    this.items = items;
	    this.acl = acl;
    }
}
