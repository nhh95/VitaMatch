package com.test.nutri.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * HealthCategory 엔티티 클래스.
 * 데이터베이스의 "healthCategories" 테이블과 매핑되며,
 * 건강 기능별로 제품 정보를 저장하는 역할을 함.
 */
@Entity
@Table(name = "healthCategories")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HealthCategory {
	
    /**
     * 기본 키 (Primary Key).
     * 데이터베이스에서 자동 생성되는 ID 값.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;

    /**
     * 제품 이미지 URL을 저장.
     * 데이터베이스의 "productImage" 열과 매핑.
     */
	@Column(name = "productImage")
	private String productImage;

    /**
     * 제품 이름을 저장.
     * 데이터베이스의 "productName" 열과 매핑.
     */
	@Column(name = "productName")
	private String productName;

    /**
     * 제품 제조 회사 이름을 저장.
     * 데이터베이스의 "companyName" 열과 매핑.
     */
	@Column(name = "companyName")
	private String productcompany;

    /**
     * 제품의 카테고리 이름을 저장.
     * 데이터베이스의 "categoryName" 열과 매핑.
     */
	@Column(name = "categoryName")
	private String categoryName;

    /**
     * 건강 기능 정보를 저장.
     * 데이터베이스의 "health" 열과 매핑.
     */
	@Column(name = "health")
	private String health;
}