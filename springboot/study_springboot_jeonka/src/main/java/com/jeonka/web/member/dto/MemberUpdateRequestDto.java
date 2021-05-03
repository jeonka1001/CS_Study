package com.jeonka.web.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {

    String email ;
    String nickName;
    String password;

    @Builder
    public MemberUpdateRequestDto (String email, String nickName, String password){
        this.email = email;
        this.nickName = nickName;
        this.password = password;
    }

}
