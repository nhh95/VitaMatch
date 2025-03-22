package com.test.nutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.nutri.entity.ReviewImage;

/**
 * ReviewImageRepository는 ReviewImage 엔티티에 대한 CRUD 작업을 수행하는 리포지토리입니다.
 * JpaRepository를 확장하여 기본적인 데이터베이스 작업을 제공하며, 추가적인 쿼리 메서드를 정의할 수 있습니다.
 * 
 * @author jiyun
 */
public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long>{}
