package com.test.nutri.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping("/board/write") //localhost:8091
	public String boardWriteForm() {
		
			return "boardwrite";
			
	}
	
}
