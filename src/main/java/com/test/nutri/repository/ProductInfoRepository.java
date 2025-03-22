package com.test.nutri.repository;

import com.test.nutri.entity.ProductInfo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository 인터페이스: ProductInfoRepository
 * 
 * 이 인터페이스는 JpaRepository를 확장하여 ProductInfo 엔티티에 대한 데이터베이스 연산을 제공합니다.
 * Spring Data JPA가 자동으로 구현체를 생성하여 CRUD 기능을 제공합니다.
 */
@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {
	
    /**
     * seq (제품 고유 식별자)를 기준으로 제품 정보를 조회하는 메서드
     *
     * @param seq 제품 고유 식별자
     * @return seq에 해당하는 ProductInfo 객체를 Optional로 반환
     */
	Optional<ProductInfo> findByseq(Long seq);
	
}
