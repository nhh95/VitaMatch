package com.test.nutri.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.nutri.model.IngredientViewDTO;
import com.test.nutri.service.IngredientViewService;

import lombok.RequiredArgsConstructor;

/**
 * 원료(Ingredient) 뷰와 관련된 요청을 처리하는 컨트롤러 클래스입니다.
 * 이 컨트롤러는 IngredientViewService를 통해 재료의 세부 정보를 가져오고,
 * 이를 View에 전달하여 화면에 표시합니다.
 */
@Controller
@RequiredArgsConstructor
public class IngredientViewController {

    // 비즈니스 로직을 처리하기 위한 서비스 클래스 의존성
    private final IngredientViewService ingredientViewService;

    /**
     * "/ingredientView" 요청을 처리합니다.
     * 특정 원료(예: "오메가3")의 정보를 조회하여 Model 객체를 통해 View에 전달합니다.
     *
     * @param model View로 데이터를 전달하기 위해 사용하는 Model 객체
     * @return 렌더링할 뷰 템플릿의 이름 (예: "page/ingredientView")
     */
    @GetMapping("/ingredientView")
    public String ingredientView(Model model) {
        // "오메가3"라는 이름의 재료 정보를 조회
        Optional<IngredientViewDTO> ingredientView = ingredientViewService
                .getIngredientViewByName("오메가3");

        // 조회된 데이터를 Model 객체에 추가
        ingredientView.ifPresent(view -> model.addAttribute("ingredientView", view));

        // View 이름 반환
        return "page/ingredientView";
    }
}
