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
    private String name;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(String email, String name, String picture, Role role){
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.role = role;
    }

    public Member update(String name, String picture){
        this.picture = picture;
        this.name = name ;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();

    }
}
