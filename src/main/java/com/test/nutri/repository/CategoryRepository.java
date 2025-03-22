package com.test.nutri.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.nutri.entity.Category;
import com.test.nutri.entity.HealthCategory;

/**
 * CategoryRepository 인터페이스.
 * 
 * Category 및 HealthCategory 엔티티와 연동되는 데이터베이스 작업을 정의.
 * JpaRepository를 확장하여 기본적인 CRUD 기능 및 페이지네이션을 지원.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * 카테고리 이름(categoryName)에 따라 Category 엔티티를 페이지네이션하여 조회.
     *
     * @param categoryName 조회할 카테고리 이름.
     * @param pageable     페이지네이션 정보(Page, Size, Sort).
     * @return Category 엔티티의 페이지 결과.
     */
    @Query("SELECT c FROM Category c WHERE c.categoryName = :categoryName")
    Page<Category> findByCategory(@Param("categoryName") String categoryName, Pageable pageable);

    /**
     * 건강 기능(health)에 따라 HealthCategory 엔티티를 페이지네이션하여 조회.
     *
     * @param category 건강 기능 카테고리 이름.
     * @param pageable 페이지네이션 정보(Page, Size, Sort).
     * @return HealthCategory 엔티티의 페이지 결과.
     */
    @Query("SELECT a FROM HealthCategory a WHERE a.health = :health")
    Page<HealthCategory> findByHealth(@Param("health") String category, Pageable pageable);
}