package com.test.nutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.nutri.entity.VwDailyRecommend;

/**
 * DailyRecommendRepository는 데이터베이스 작업을 수행하는 리포지토리 인터페이스입니다.
 * VwDailyRecommend 엔티티에 대한 기본적인 CRUD 작업.
 * @author Yujin Kim
 */
public interface DailyRecommendRepository extends JpaRepository<VwDailyRecommend, Long> {

	@Query("select d from VwDailyRecommend d where d.dailySeq = ?1")
	List<VwDailyRecommend> listAll(int dailySeq);

	@Query("select d from VwDailyRecommend d where d.dailySeq = ?1")
	List<VwDailyRecommend> findAll(String dailySeq);
	
}
