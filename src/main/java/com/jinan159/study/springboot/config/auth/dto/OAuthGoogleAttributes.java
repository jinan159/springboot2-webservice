package com.jinan159.study.springboot.config.auth.dto;

import java.util.Map;

public class OAuthGoogleAttributes extends OAuthAttributes {
    public OAuthGoogleAttributes(Map<String, Object> attributes, String userNameAttributeName) {
        this.name = (String) attributes.get("name");
        this.email = (String) attributes.get("email");
        this.picture = (String) attributes.get("picture");
        this.attributes = attributes;
        this.nameAttributeKey = userNameAttributeName;
    }
}
