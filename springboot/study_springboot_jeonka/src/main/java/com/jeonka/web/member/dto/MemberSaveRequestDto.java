package com.jeonka.web.member.dto;

import com.jeonka.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private String email;
    private String password;
    private String nickName;

    @Builder
    public MemberSaveRequestDto(String email,String password,String nickName){
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    public Member toEntity(){
        return Member.builder().email(email).password(password).nickName(nickName).build();
    }
}
