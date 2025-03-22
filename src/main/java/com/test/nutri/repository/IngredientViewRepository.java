package com.test.nutri.repository;


/**
 * Repository 인터페이스: IngredientViewRepository
 * 
 * 이 인터페이스는 JpaRepository를 확장하여 IngredientView 엔티티에 대한 데이터베이스 연산을 제공합니다.
 * Spring Data JPA가 자동으로 구현체를 생성하여  사용자 정의 쿼리 메서드를 제공합니다.
 */
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.nutri.entity.IngredientView;

public interface IngredientViewRepository extends JpaRepository<IngredientView, Integer> {
	
	
    /**
     * ingredientName (영양소 이름)을 기준으로 데이터를 조회하는 메서드
     *
     * @param ingredientName 영양소 이름
     * @return ingredientName에 해당하는 IngredientView 객체를 Optional로 반환
     */
    Optional<IngredientView> findByIngredientName(String ingredientName);
}