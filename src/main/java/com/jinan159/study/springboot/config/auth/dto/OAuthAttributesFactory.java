package com.jinan159.study.springboot.config.auth.dto;

import com.jinan159.study.springboot.domain.user.Role;
import com.jinan159.study.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributesFactory {

    public static OAuthAttributes getOAuthAttributes(SocialLoginType type, Map<String, Object> attributes, String userNameAttributeName) {
        switch (type) {
            case GOOGLE: return new OAuthGoogleAttributes(attributes, userNameAttributeName);
            case NAVER: return new OAuthNaverAttributes(attributes);
            default: throw new IllegalArgumentException("Invalid SocialLoginType");
        }
    }
}
