package com.test.nutri.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.nutri.entity.Member;
import com.test.nutri.model.MemberDTO;
import com.test.nutri.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder	bCryptPasswordEncoder;
	
	/**
	 * 회원가입 처리를 위한 메서드입니다.
	 * 이미 사용 중인 아이디일 경우 콘솔에 이미 사용 중인 아이디라고 출력해주고 DB에 저장 없이 메서드를 종료합니다.
	 * 그렇지 않은 경우에 해당 DTO객체를 Entity 객체로 반환시켜 준 후에 DB에 저장합니다.
	 * @param dto 회원가입 정보
	 * @author Sangsoo Jeon
	 * 
	 */
	public void signup(MemberDTO dto) {
		
		boolean result = memberRepository.existsByUsername(dto.getUsername());
		
		if (dto.getTelephone1().isEmpty() ||
			    dto.getTelephone2().isEmpty() ||
			    dto.getTelephone3().isEmpty()) {
			    throw new IllegalArgumentException("전화번호를 다시 입력하세요.");
			} else {
				String telephone = dto.getTelephone1() + "-" + dto.getTelephone2() + "-" + dto.getTelephone3();
				dto.setTelephone(telephone);
			}

		
		if (result) {
			System.out.println("이미 사용중인 아이디입니다.");
			return;
		}
		
		Member member = Member.builder()
							.username(dto.getUsername())
							.email(dto.getEmail())
							.password(bCryptPasswordEncoder.encode(dto.getPassword()))
							.name(dto.getName())
							.nickname(dto.getNickname())
							.dob(dto.getDob())
							.gender(dto.getGender())
							.telephone(dto.getTelephone())
							.zipcode(dto.getZipcode())
							.address(dto.getAddress())
							.addressDetail(dto.getAddressDetail())
							.addressExtra(dto.getAddressExtra())
							.build();
		
		memberRepository.save(member);
	}

/**
 * 주어진 사용자 이름이 사용 가능한지 확인합니다.
 * 
 * @param username 확인할 사용자 이름
 * @return 사용자 이름이 사용 가능한 경우 true, 그렇지 않으면 false
 * @author Sangsoo Jeon
 */
	public boolean isUsernameAvailable(String username) {
		
		return !memberRepository.existsByUsername(username); 
	}
	
}
