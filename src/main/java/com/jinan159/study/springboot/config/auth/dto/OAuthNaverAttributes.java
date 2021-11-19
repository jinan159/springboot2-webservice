package com.jinan159.study.springboot.config.auth.dto;

import java.util.Map;

public class OAuthNaverAttributes extends OAuthAttributes {
    public OAuthNaverAttributes(Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        this.name = (String) response.get("name");
        this.email = (String) response.get("email");
        this.picture = (String) response.get("picture");
        this.attributes = response;
        this.nameAttributeKey = "id";
    }
}
