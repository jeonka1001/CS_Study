package com.jeonka.config.auth.dto;

import com.jeonka.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User member){

        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
    }
}
