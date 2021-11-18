package com.jinan159.study.springboot.config.auth.dto;

import com.jinan159.study.springboot.domain.user.Role;
import com.jinan159.study.springboot.domain.user.User;
import lombok.Getter;

import java.util.Map;

@Getter
public abstract class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    /*
    - OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때임
    - 가입할 때의 기본 권한을 GUEST로 설정하기 위해, builder에서 Role.GUEST를 사용하였음
     */
    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .picture(this.picture)
                .role(Role.GUEST)
                .build();
    }
}
