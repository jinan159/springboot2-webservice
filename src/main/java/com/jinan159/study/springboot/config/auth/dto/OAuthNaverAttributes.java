package com.jinan159.study.springboot.config.auth.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthNaverAttributes extends OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    public OAuthNaverAttributes(Map<String, Object> attributes, String userNameAttributeName) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        this.name = (String) response.get("name");
        this.email = (String) response.get("email");
        this.picture = (String) response.get("picture");
        this.attributes = response;
        this.nameAttributeKey = userNameAttributeName;
    }
}
