package com.jeonka.config.auth.dto;

import com.jeonka.domain.member.Member;
import com.jeonka.domain.member.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String,Object> attributes, String nameAttributeKey, String name, String email, String picture){
        this.attributes = attributes;
        this.name = name;
        this.nameAttributeKey = nameAttributeKey;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registerationId, String userNameAttributeName, Map<String,Object> attributes){
        return ofGoogle(userNameAttributeName,attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object>attributes){
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .picture((String)attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    public Member toEntity(){
        return Member.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
