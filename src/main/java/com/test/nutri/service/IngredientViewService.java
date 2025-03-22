package com.test.nutri.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.test.nutri.entity.IngredientView;
import com.test.nutri.model.IngredientViewDTO;
import com.test.nutri.repository.IngredientViewRepository;

import lombok.RequiredArgsConstructor;


/**
 * Service 클래스: IngredientViewService
 * 
 * 이 클래스는 IngredientView와 관련된 비즈니스 로직을 처리합니다.
 * Repository와 통신하여 데이터를 조회하고 필요한 경우 DTO로 변환하여 반환합니다.
 */
@Service
@RequiredArgsConstructor
public class IngredientViewService {

    private final IngredientViewRepository ingredientViewRepository;
    
    /**
     * 주어진 영양소 이름(ingredientName)을 기준으로 데이터를 조회하고,
     * 조회된 데이터를 IngredientViewDTO로 변환하여 반환합니다.
     *
     * @param ingredientName 조회할 영양소 이름
     * @return 조회된 IngredientView 데이터를 DTO로 변환하여 Optional로 반환
     */
    public Optional<IngredientViewDTO> getIngredientViewByName(String ingredientName) {
        return ingredientViewRepository.findByIngredientName(ingredientName)
                .map(IngredientView::toDTO);
    }
}