package com.test.nutri.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.nutri.entity.ProductInfo;
import com.test.nutri.repository.ProductInfoRepository;

import lombok.RequiredArgsConstructor;


/**
 * Product 정보와 관련된 요청을 처리하는 컨트롤러 클래스입니다.
 * 이 컨트롤러는 ProductInfoRepository를 통해 데이터베이스에서 제품 정보를 조회하고,
 * 조회된 데이터를 View로 전달하여 화면에 표시합니다.
 */
@Controller
@RequiredArgsConstructor
public class ProductInfoController {

	private final ProductInfoRepository productrepo;
	
	
    /**
     * "/productInfo" 요청을 처리합니다.
     * 주어진 seq(제품의 고유 식별자)를 기준으로 제품 정보를 조회하고,
     * 조회된 데이터를 Model 객체를 통해 View에 전달합니다.
     *
     * @param model View로 데이터를 전달하기 위해 사용하는 Model 객체
     * @param seq 제품 고유 식별자 (Request Parameter로 전달받음)
     * @return 렌더링할 뷰 템플릿의 이름 (예: "page/productInfo")
     */
	@GetMapping("/productInfo")
	public String productInfo(Model model,@RequestParam(name = "seq")long seq) {
		
		
		// seq를 사용하여 데이터베이스에서 제품 정보 조회
		Optional<ProductInfo> productInfoEntity = productrepo.findByseq(seq);

		// 조회된 제품 정보를 Model 객체에 추가 (DTO 변환 후 추가)
		productInfoEntity.ifPresent(value -> model.addAttribute("productInfo", value.toDTO()));
		
		  // View 이름 반환
		return "page/productInfo";
	}
	
}
