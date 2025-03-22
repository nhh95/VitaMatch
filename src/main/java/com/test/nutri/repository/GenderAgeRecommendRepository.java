package com.test.nutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.nutri.entity.VwGenderAgeRecommend;

/**
 * GenderAgeRecommendRepository는 데이터베이스 작업을 수행하는 리포지토리 인터페이스입니다.
 * VwGenderAgeRecommend 엔티티에 대한 기본적인 CRUD 작업.
 * @author Yujin Kim
 */
public interface GenderAgeRecommendRepository extends JpaRepository<VwGenderAgeRecommend, Long> {

	@Query("select ga from VwGenderAgeRecommend ga where ga.genderAgeSeq = ?1	")
	List<VwGenderAgeRecommend> listAll(int genderAgeSeq);

	@Query("select ga from VwGenderAgeRecommend ga where ga.gender = ?1 and ga.age = ?2	")
	List<VwGenderAgeRecommend> findAll(String gender, String age);
	
}
