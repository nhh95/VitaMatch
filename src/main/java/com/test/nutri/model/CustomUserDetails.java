package com.test.nutri.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.test.nutri.entity.Member;

import lombok.Getter;

 /**
 * Spring Security에서 제공하는 UserDetailsService 인터페이스를 구현한 클래스입니다.
 * 사용자 정보를 불러와 UserDetails 형식으로 반환합니다.
 */
@Getter
public class CustomUserDetails implements UserDetails {

    private Member member;

    /**
     * 생성자를 제공합니다.
     */
    public CustomUserDetails(Member member) {
        this.member = member;
    }

    @Override
    /**
     * 사용자의 권한을 반환합니다.
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                String role = "ROLE_MEMBER";
                return role;
            }
        });

        return authorities;
    }

    @Override
    /**
     * 사용자의 암호를 반환합니다.
	 * @return 사용자 암호
     */
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    /**
     * 사용자의 아이디를 반환합니다.
     * @return 사용자 ID
     */
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    /**
     * 현재 계정이 만료된 계정인지 아닌지를 확인합니다.
     * @return 만료되지 않은 계정이면 true, 만료된 계정이면 false를 반환합니다.
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    /**
     * 계정 잠금 상태를 반환합니다.
     */
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    /**
     * 비밀번호에 대한 만료 상태를 반환합니다.
     * @return 만료되지 않으면 true, 만료됐으면 false를 반환합니다(비밀번호 변경이 필요하다는 의미).
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 사용자의 계정이 활성화 상태인지 잠금상태인지를 확인합니다.
     * @return 활성화 상태이면 true, 잠금 상태이면 false를 반환합니다.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    //지윤 추가한 부분
    public String getNickname() {
        return member.getNickname();  
    }
}
