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
public class Acl {
	private String creator;
	private List<String> r = new ArrayList<>();
	private List<String> w = new ArrayList<>();

	public Acl(String creator) {
		this.creator = creator;
	}
}
