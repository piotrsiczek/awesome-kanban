package com.spiczek.kanban.model;

import com.spiczek.kanban.collections.Board;
import com.spiczek.kanban.config.AuthUser;
import lombok.Getter;

import java.util.List;

/**
 * @author Piotr Siczek
 */
@Getter
public class UserResult {
    private String id;
    private String login;
    private String name;
    private String surname;
    private List<Board> boards;

	public UserResult(AuthUser user, List<Board> boards) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.boards = boards;
	}
}
