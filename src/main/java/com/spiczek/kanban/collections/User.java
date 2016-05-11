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
public class User {
    private String id;
    private String login;
    private String name;
    private String surname;
    private String password;
	private List<String> roles = new ArrayList<>();
    private List<String> boardIds = new ArrayList<>();
    private List<String> invitedBoardIds = new ArrayList<>();

    public User(String login, String name, String surname, String password) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
}
