package com.jeonka.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=50, nullable = false)
    private String email;

    @Column(length=15, nullable = false)
    private String nickName;

    @Column(length=16, nullable = false)
    private String password;

    @Builder
    public Member(String email, String nickName, String password){
        this.email = email;
        this.nickName = nickName;
        this.password = password;
    }

    public void update(String email, String nickName, String password){
        this.email = email;
        this.nickName = nickName ;
        this.password = password;
    }
}
