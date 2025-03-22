package com.test.nutri.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.test.nutri.entity.ShoppingCartInfo;
import com.test.nutri.model.CustomUserDetails;
import com.test.nutri.model.ShoppingCartDTO;
import com.test.nutri.repository.ShoppingCartRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ShoppingCartController {
	
	@PostMapping("/shoppingcart/add")
	public ResponseEntity<?> addToCart(@RequestBody ShoppingCartDTO request) {
		
		System.out.println("Received ProductInfoSeq: " + request.getProductInfoSeq());
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication == null || !authentication.isAuthenticated()) {
	        return ResponseEntity.status(403).body("사용자가 인증되지 않았습니다.");
	    }

	    Object principal = authentication.getPrincipal();

	    if (principal instanceof CustomUserDetails) {
	        CustomUserDetails userDetails = (CustomUserDetails) principal;
	        Integer seq = userDetails.getMember().getSeq();

	        shoppingCartRepository.insertIntoShoppingCart(seq, request.getProductInfoSeq());
	        
	        return ResponseEntity.ok("상품이 장바구니에 추가되었습니다.");
	    }

	    return ResponseEntity.status(403).body("사용자가 인증되지 않았습니다.");
	}
	
	private final ShoppingCartRepository shoppingCartRepository;
	
	@GetMapping("/shoppingcart")
	public String shoppingcart(Model model,  HttpServletResponse response) throws IOException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Object principal = authentication.getPrincipal();
    	
    	Integer seq = null;
    	
    	if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            seq = userDetails.getMember().getSeq();
            System.out.println("현재 회원 seq: " + seq);
        } else {
            response.sendRedirect("/login");
            return null;
        }
    	
		List<ShoppingCartInfo> shoppingCartItem = shoppingCartRepository.findShoppingCartDetails(seq);
		    
	    if (shoppingCartItem == null || shoppingCartItem.isEmpty()) {
	        System.out.println("쇼핑 카트에 데이터가 없습니다.");
		}
	    
	    System.out.println(shoppingCartItem);

		model.addAttribute("shoppingCartItem", shoppingCartItem);
		
		return "page/shoppingCart";
	}
}
