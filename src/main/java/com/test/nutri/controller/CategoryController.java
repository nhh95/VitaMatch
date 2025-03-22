package com.test.nutri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.nutri.model.CustomUserDetails;
import com.test.nutri.repository.CategoryRepository;

/**
 * 카테고리별 영양제 및 건강 기능 제품을 조회하는 컨트롤러.
 * 사용자가 선택한 카테고리에 따라 페이지네이션된 제품 리스트를 제공.
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 성분 기반 제품 카테고리 조회를 처리하는 메서드.
     *
     * @param model    뷰로 데이터를 전달하기 위한 모델 객체.
     * @param category 조회할 제품의 성분 카테고리 (선택 사항).
     * @param page     페이지 번호 (기본값: 0).
     * @return 성분 기반 제품 페이지를 렌더링하기 위한 Thymeleaf 템플릿 이름.
     */
    @GetMapping("/ingredient")
    public String ingredient( Model model, @RequestParam(name = "category", required = false) String category,
    		@RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
    	
        Pageable pageable = PageRequest.of(page, 9);
        Page<?> categoryPage = categoryRepository.findByCategory(category, pageable);
        
        model.addAttribute("list", categoryPage.getContent());     
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("currentPage", page);                     
        model.addAttribute("category", category);                      
        
        return "page/ingredient";
    }

    /**
     * 건강 기능 기반 제품 카테고리 조회를 처리하는 메서드.
     *
     * @param model    뷰로 데이터를 전달하기 위한 모델 객체.
     * @param category 조회할 건강 기능 카테고리 (선택 사항).
     * @param page     페이지 번호 (기본값: 0).
     * @return 건강 기능 기반 제품 페이지를 렌더링하기 위한 Thymeleaf 템플릿 이름.
     */
    @GetMapping("/health")
    public String map( Model model, @RequestParam(name = "category", required = false) String category, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        
        Pageable pageable = PageRequest.of(page, 9);
        Page<?> categoryHealth = categoryRepository.findByHealth(category, pageable);
        
        model.addAttribute("list", categoryHealth.getContent());    
        model.addAttribute("totalPages", categoryHealth.getTotalPages());
        model.addAttribute("currentPage", page);                       
        model.addAttribute("category", category);                       
        
        return "page/health";
    }
}
