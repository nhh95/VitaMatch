package com.test.nutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.nutri.entity.VwBadCombination;

/**
 * BadCombinationRepository
 * VwBadCombination 엔티티를 처리하는 JPA 리포지토리 인터페이스
 * 기본적인 CRUD 작업을 진행
 * @author bohwa Jang
 */
public interface BadCombinationRepository extends JpaRepository<VwBadCombination, Long> {

	List<VwBadCombination> findByIngredientSeq(String ingredientSeq);

	@Query("select v from VwBadCombination v")
	List<VwBadCombination> listAll();

}
