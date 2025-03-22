package com.test.nutri.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.nutri.service.NutriService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

	private final NutriService nutriService;
	
	@GetMapping("/login")
	public String login(Model model) {
		return "page/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication); //로그아웃
		}
		
		return "redirect:/";
	}
	
}
