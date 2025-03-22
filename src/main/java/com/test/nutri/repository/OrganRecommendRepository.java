package com.test.nutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.nutri.entity.VwOrganRecommend;

/**
 * OrganRecommendRepository는 데이터베이스 작업을 수행하는 리포지토리 인터페이스입니다.
 * VwOrganRecommend 엔티티에 대한 기본적인 CRUD 작업.
 * @author Yujin Kim
 */
public interface OrganRecommendRepository extends JpaRepository<VwOrganRecommend, Long> {

	@Query("select o from VwOrganRecommend o where o.organSeq = ?1")
	List<VwOrganRecommend> listAll(int organSeq);

	@Query("select o from VwOrganRecommend o where o.organSeq = ?1")
	List<VwOrganRecommend> findAll(String organSeq);

	
}
