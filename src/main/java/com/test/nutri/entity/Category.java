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
 * Category 엔티티 클래스.
 * 데이터베이스의 "categories" 테이블과 매핑되며, 영양제 및 건강 제품의 정보를 담고 있음.
 */
@Entity
@Table(name = "healthcategories")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    /**
     * 기본 키 (Primary Key).
     * 데이터베이스에서 자동 생성되는 ID 값.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    private Long id;

    /**
     * 제품 이미지 URL을 저장.
     * 테이블의 "productImage" 열과 매핑.
     */
    @Column(name = "productImage")
    private String productImage;

    /**
     * 제품 이름을 저장.
     * 테이블의 "productName" 열과 매핑.
     */
    @Column(name = "productName")
    private String productName;

    /**
     * 제품 제조 회사 이름을 저장.
     * 테이블의 "companyName" 열과 매핑.
     */
    @Column(name = "companyName")
    private String productcompany;

    /**
     * 제품의 카테고리 이름을 저장.
     * 테이블의 "categoryName" 열과 매핑.
     */
    @Column(name = "categoryName")
    private String categoryName;
}