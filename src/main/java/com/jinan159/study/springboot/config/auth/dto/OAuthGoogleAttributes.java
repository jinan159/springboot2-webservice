package com.jinan159.study.springboot.config.auth.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthGoogleAttributes extends OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    public OAuthGoogleAttributes(Map<String, Object> attributes, String userNameAttributeName) {
        this.name = (String) attributes.get("name");
        this.email = (String) attributes.get("email");
        this.picture = (String) attributes.get("picture");
        this.attributes = attributes;
        this.nameAttributeKey = userNameAttributeName;
    }
}
