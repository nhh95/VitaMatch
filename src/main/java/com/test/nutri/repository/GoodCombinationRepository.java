package com.test.nutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.nutri.entity.VwGoodCombination;

/**
 * GoodCombinationRepository
 * VwGoodCombination 엔티티를 처리하는 JPA 리포지토리 인터페이스
 * 기본적인 CRUD 작업을 진행
 * @author bohwa Jang
 */
public interface GoodCombinationRepository extends JpaRepository<VwGoodCombination, Long>{

	List<VwGoodCombination> findByIngredientSeq(String ingredientSeq);

	@Query("select v from VwGoodCombination v")
	List<VwGoodCombination> listAll();

}
