package com.spiczek.kanban.controllers;

import com.spiczek.kanban.apis.BoardApi;
import com.spiczek.kanban.collections.Board;
import com.spiczek.kanban.config.AuthUser;
import com.spiczek.kanban.model.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@RestController
public class LoginController {
	private BoardApi boardApi;

    @Autowired
    public LoginController(BoardApi boardApi) {
	    this.boardApi = boardApi;
    }

	@RequestMapping(value = "/user/data")
	public UserResult getUserData() {
		AuthUser user = (AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Board> boards = boardApi.getUserBoards(user.getBoardIds());

		return new UserResult(user, boards);
	}
}
