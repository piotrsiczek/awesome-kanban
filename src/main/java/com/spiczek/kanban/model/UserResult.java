package com.spiczek.kanban.model;

import com.spiczek.kanban.collections.Board;
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

    public UserResult(String id, String login, String name, String surname, List<Board> boards) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.boards = boards;
    }
}
