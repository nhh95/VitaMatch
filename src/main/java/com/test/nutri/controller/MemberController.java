package com.test.nutri.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.nutri.model.MemberDTO;
import com.test.nutri.service.MemberService;
import lombok.RequiredArgsConstructor;

/**
 * 회원 정보와 관련된 요청을 처리하는 컨트롤러 클래스입니다.
 * 클래스는 회원가입, 회원 정보 확인 등의 기능을 제공합니다.
 *
 * @author Sangsoo Jeon
 */
@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	/**
	 * 회원 가입 페이지를 표시합니다.
	 *
	 * @param model 뷰에서 사용할 모델
	 * @return 회원 가입 페이지
	 */
	@GetMapping("/signup")
	public String signup(Model model) {
		return "page/signup";
	}
	
	/**
	 * 회원 가입을 처리합니다.
	 *
	 * @param dto 회원 가입 정보
	 * @return 로그인 페이지
	 */
	@PostMapping("/signupok")
	public String signupok(MemberDTO dto) {
		
		//dto.combineTelephone();
		
		System.out.println("dto: " + dto);
		
		memberService.signup(dto);
		
		return "redirect:/login";
	}
	
	/**
	 * 아이디 중복 확인을 처리합니다.
	 *
	 * @param username 중복 확인할 아이디
	 * @return 중복 확인 결과
	 */
	@GetMapping("/checkusername")
	@ResponseBody
	public ResponseEntity<Boolean> checkUsername(@RequestParam("username") String username) {
		
		boolean isAvailable = memberService.isUsernameAvailable(username);
		
		return ResponseEntity.ok(isAvailable);
		
	}
	
	
}

