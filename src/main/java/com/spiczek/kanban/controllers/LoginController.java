package com.spiczek.kanban.controllers;


import com.spiczek.kanban.apis.UserApi;
import com.spiczek.kanban.model.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Piotr Siczek
 */
@RestController
public class LoginController {

    private UserApi api;

    @Autowired
    public LoginController(UserApi api) {
        this.api = api;
    }

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

//    @RequestMapping("/login")
//    public ResponseEntity<UserResult> loginUser() {
//        return new ResponseEntity<>(api.loginUser(), HttpStatus.OK);
//    }
}
