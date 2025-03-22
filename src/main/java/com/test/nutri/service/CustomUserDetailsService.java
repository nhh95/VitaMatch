package com.test.nutri.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.nutri.entity.Member;
import com.test.nutri.model.CustomUserDetails;
import com.test.nutri.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

/**
 * Spring Security에서 제공하는 UserDetailsService 인터페이스를 구현.
 * 사용자 로그인 처리를 담당하는 클래스.
 *
 * @author Sangsoo Jeon
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	//로그인 처리 -> DB 사용
	private final MemberRepository memberRepository;

	//사용자 -> 로그인 시도(username, password) -> loadUserByUsername 호출
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//쿼리 작성
		//SELECT * FROM member WHERE username = ?
		Member member = memberRepository.findByUsername(username);
		
		//쿼리로 받아온 결과에 대해서 null 체크
		if (member != null) {
			
			return new CustomUserDetails(member); //로그인 성공
			//return new CustomUserDetails;
		}
		
		return null; //로그인 실패
	}
	
}

