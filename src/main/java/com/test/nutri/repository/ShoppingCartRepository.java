package com.test.nutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.test.nutri.entity.ShoppingCartInfo;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartInfo, Long> {

	@Query(value = """
	        SELECT 
	            S.seq AS id, 
	            S.member_seq AS member_seq, 
	            S.productInfo_seq AS productInfo_seq, 
	            S.amount AS amount, 
	            S.status AS status, 
	            P.productImage AS productImage,
	            P.CompanyName AS companyName, 
	            P.productName AS productName, 
	            PP.price AS price
	        FROM shoppingCart S
	        INNER JOIN productInfo P ON S.productInfo_seq = P.seq
	        INNER JOIN productPrice PP ON S.productInfo_seq = PP.productInfo_seq
	        WHERE S.member_seq = :memberSeq
	    """, nativeQuery = true)
	List<ShoppingCartInfo> findShoppingCartDetails(@Param("memberSeq") Integer seq);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO shoppingCart (member_seq, productInfo_seq, add_date) " +
	           "VALUES (:seq, :productInfoSeq, CURRENT_DATE)", nativeQuery = true)
	void insertIntoShoppingCart(@Param("seq") Integer seq, @Param("productInfoSeq") Long productInfoSeq);
	
}