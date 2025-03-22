package com.test.nutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.nutri.entity.Review;
import com.test.nutri.entity.VwReview;

/**
 * ReviewRepository는 VwReview 엔티티에 대한 CRUD 작업을 수행하는 리포지토리입니다.
 * JpaRepository를 확장하여 기본적인 데이터베이스 작업을 제공하며, Review 엔티티의 저장 작업을 추가로 정의할 수 있습니다.
 * 
 * @author jiyun
 */
public interface ReviewRepository extends JpaRepository<VwReview, Long>{

	/**
     * Review 엔티티를 데이터베이스에 저장합니다.
     * 
     * @param review 저장할 Review 엔티티
     */
	void save(Review review);
	
}
