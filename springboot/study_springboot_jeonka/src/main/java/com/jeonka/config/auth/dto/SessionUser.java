package com.jeonka.config.auth.dto;

import com.jeonka.domain.member.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(Member member){
        System.out.println(">>>>>>>>>>>>>>>>>>>>SessionUser 생성 <<<<<<<<<<<<<<<<<<<<<<<");
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
    }
}
