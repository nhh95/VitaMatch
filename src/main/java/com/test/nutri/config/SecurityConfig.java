package com.test.nutri.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 구성 클래스입니다.
 * 
 * 이 클래스는 애플리케이션의 보안 구성을 담당하며, HTTP 요청에 대한 보안 필터 체인을 정의하고,
 * 암호화 객체와 로그아웃 설정을 제공합니다.
 * 
 * 기존에 리소스, 페이지 접근, CSRF 토큰 설정이 혼합되어 있었으므로 이를 분리하여 설정합니다.
 * 
 * 정적 리소스 접근 허용, 페이지 접근 허용, 커스텀 로그인 설정, 암호화 객체 생성, 로그아웃 설정 등을 포함합니다.
 *
 * @author Sangsoo Jeon
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 보안 필터 체인을 정의하는 메서드.
     * 
     * @param http HTTP 보안 설정 객체
     * @return 보안 필터 체인 객체
     * @throws Exception 설정 중 발생할 수 있는 예외
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		//기존에 리소스 + 페이지 접근 + csrf 토큰 설정 다 섞여 있어서 분리 시켰습니다.

        // 정적 리소스 접근 허용
        http.headers((headerConfig) -> headerConfig.frameOptions((frameOptionConfig -> frameOptionConfig.disable())));

        // 페이지 접근 허용
        http.authorizeHttpRequests((auth) -> 
            auth
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
        );

        http.csrf(csrf -> csrf
            .ignoringRequestMatchers("/shoppingcart/add")
        );

		//CSRF 토큰 해제
		//http.csrf(auth -> auth.disable());
        // 커스텀 로그인 설정
        http.formLogin(auth -> auth
                .loginPage("/login") // 사용자 로그인 페이지 URL
                .defaultSuccessUrl("/") // 로그인 성공시 페이지 URL
                .loginProcessingUrl("/loginok").permitAll()
        );

        return http.build();
    }

    /**
     * 암호화 객체를 생성하는 메서드.
     * 
     * @return BCryptPasswordEncoder 객체
     **/
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 로그아웃 설정을 정의하는 메서드.
     * 
     * @param http HTTP 보안 설정 객체
     * @return 보안 필터 체인 객체
     * @throws Exception 설정 중 발생할 수 있는 예외
     **/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.logout(auth -> auth
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        );

        return http.build();
    }

}
