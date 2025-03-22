package com.test.nutri.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.nutri.service.NutriService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NutriController {

	private final NutriService nutriService;
	
	@GetMapping("/")
	public String main(Model model) {
		return "main";
	}
}
