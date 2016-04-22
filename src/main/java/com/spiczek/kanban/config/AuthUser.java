package com.spiczek.kanban.config;

import com.spiczek.kanban.collections.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Getter
public class AuthUser extends org.springframework.security.core.userdetails.User {
    private String id;
    private String email;
    private String surname;

    public AuthUser(User user) {
        super(user.getName(), user.getPassword(), asList(user.getRoles()));
        this.id = user.getId();
        this.surname = user.getSurname();
    }

    private static List<GrantedAuthority> asList(List<String> roles) {
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            result.add(new SimpleGrantedAuthority(role));
        }
        return result;
    }
}
