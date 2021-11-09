package com.jinan159.study.springboot.config.auth;

import com.jinan159.study.springboot.config.auth.dto.OAuthAttributes;
import com.jinan159.study.springboot.config.auth.dto.SessionUser;
import com.jinan159.study.springboot.domain.user.User;
import com.jinan159.study.springboot.domain.user.UserReporitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserReporitory userReporitory;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행 중인 서비스를 구분하는 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        /*
        - OAuth2 로그인 진행 시 키가 되는 필드값임(PK 같은 의미)
        - 구글의 경우 기본적으로 "sub" 라는 기본키를 지원함(네이버, 카카오는 지원하지 않는다고 함)
         */
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        /*
        - OAuth2UserService 를 통해 가져온 OAuth2User 의 attribute 를 담을 클래스
        - 타 소셜 로그인도 이 클래스를 사용함
         */
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);


        /*
         - 세션에 사용자 정보를 저장하기 위함 DTO 클래스
         - TODO : 왜 User 클래스를 쓰지 않고, SessionUser 를 새로 만들어서 쓰는지 작성하기
         */
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userReporitory.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userReporitory.save(user);
    }
}
