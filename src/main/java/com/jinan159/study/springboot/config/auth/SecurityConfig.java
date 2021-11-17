package com.jinan159.study.springboot.config.auth;

import com.jinan159.study.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화 시킴
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면 사용을 위해 disable 한 옵션
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    // URL 별 권한 관리를 설정하는 옵션의 시작점
                    .authorizeRequests()
                    /*
                    - 권한관리 대상자를 지정하는 옵션
                    - URL, HTTP Method 별로 관리 가능함
                     */
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() // Public 으로 열어둘 URL 들은 전체 열람권한 설정
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // /api/v1/** 경로의 API 는 USER 권한을 가진 사람만 가능하도록 설정
                    .anyRequest().authenticated() // 설정된 값 이외의 나머지 URL 들은 인증된 사용자들 에게만 허용(로그인한 사용자)
                .and()
                    // 로그아웃 기능에 대한 여러 설정의 진입점
                    .logout()
                        .logoutSuccessUrl("/") // 로그안웃 성공 시 redirect url
                .and()
                    // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                    .oauth2Login()
                        // OAuth2 로그인 성공 후 사용자 정보를 가져올 때의 설정들을 담당함
                        .userInfoEndpoint()
                            /*
                            - 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록함
                            - 리소스 서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행할 기능을 명시
                             */
                            .userService(customOAuth2UserService);

    }
}
