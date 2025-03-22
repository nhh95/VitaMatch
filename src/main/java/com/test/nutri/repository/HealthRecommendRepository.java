package com.test.nutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.nutri.entity.VwHealthRecommend;

/**
 * HealthRecommendRepository는 데이터베이스 작업을 수행하는 리포지토리 인터페이스입니다.
 * VwHealthRecommend 엔티티에 대한 기본적인 CRUD 작업.
 * @author Yujin Kim
 */
public interface HealthRecommendRepository extends JpaRepository<VwHealthRecommend, Long> {

	@Query("select h from VwHealthRecommend h where h.healthSeq = ?1")
	List<VwHealthRecommend> listAll(int healthSeq);

	@Query("select h from VwHealthRecommend h where h.healthSeq = ?1")
	List<VwHealthRecommend> findAll(String healthSeq);
	
}
