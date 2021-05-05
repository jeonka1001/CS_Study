package com.jeonka.web.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDeleteRequestDto {
    private String email;
    private String nickName;
    private String password;

    @Builder
    public MemberDeleteRequestDto(String email, String nickName, String password){
        this.email = email;
        this.nickName = nickName;
        this.password = password;
    }
}
