package com.test.nutri.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.nutri.entity.QReview;
import com.test.nutri.entity.QReviewImage;
import com.test.nutri.entity.QVwReview;
import com.test.nutri.entity.VwReview;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * ReviewQueryDSLRepository는 QueryDSL을 이용하여 리뷰 데이터를 조회, 수정, 삭제하는 기능을 제공합니다.
 * 이 클래스는 리뷰 목록 페이징 처리, 키워드 검색, 리뷰 수정 및 삭제와 같은 작업을 지원합니다.
 * 
 * @author jiyun
 */
@Repository
@RequiredArgsConstructor
public class ReviewQueryDSLRepository {

	private final JPAQueryFactory jpaQueryFactory;
	private final QVwReview vwReview = QVwReview.vwReview;	//Q 클래스 선언
	private final QReview review = QReview.review;	
	private final QReviewImage reviewImage = QReviewImage.reviewImage;	
	
	/**
	 * 주어진 리뷰 고유 식별자(seq)에 해당하는 리뷰 정보를 반환합니다.
	 * 
	 * @param seq 리뷰의 고유 식별자
	 * @return 해당 리뷰의 VwReview 객체, 없으면 null
	 */
	// seq가 일치하는 리뷰 필터링
	public VwReview findReviewBySeq(Long seq) {
		
		return jpaQueryFactory
				.selectFrom(vwReview)
				.where(vwReview.seq.eq(seq))
				.fetchOne();
	}
	
	/**
	 * 페이징 처리된 전체 리뷰 리스트를 반환합니다.
	 * 
	 * @param offset 페이지 오프셋 (시작 위치)
	 * @param limit 페이지 제한 (가져올 리뷰 수)
	 * @return 페이징된 리뷰 리스트
	 */
	// 전체 리뷰 페이징
	public List<VwReview> findAllPagination(int offset, int limit) {
		
		return jpaQueryFactory
					.selectFrom(vwReview)
					.offset(offset)	//offset: 몇번째부터 가져와라
					.limit(limit)	//limit: 몇개까지 가져와라
					.fetch();
	}

	/**
	 * 전체 리뷰의 개수를 반환합니다.
	 * 
	 * @return 전체 리뷰 개수
	 */
	// 전체 리뷰 개수
	public int count() {
		
		return (int)jpaQueryFactory
					.selectFrom(vwReview)
					.fetchCount();
	}
	
	/**
	 * 주어진 키워드로 리뷰를 검색하고 페이징 처리된 결과를 반환합니다.
	 * 키워드는 제목, 카테고리, 내용, 영양제 이름을 기준으로 검색됩니다.
	 * 
	 * @param offset 페이지 오프셋 (시작 위치)
	 * @param limit 페이지 제한 (가져올 리뷰 수)
	 * @param keyword 검색 키워드
	 * @return 검색된 리뷰 리스트
	 */
	// keyword 검색
	public List<VwReview> search(int offset, int limit, String keyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
	    if (keyword != null && !keyword.trim().isEmpty()) {
	        builder.or(vwReview.category.contains(keyword))
	               .or(vwReview.title.contains(keyword))
	               .or(vwReview.content.contains(keyword))
	               .or(vwReview.name.contains(keyword));
	    }

	    return jpaQueryFactory
	            .selectFrom(vwReview)
	            .where(builder)
	            .orderBy(vwReview.seq.desc())
	            .offset(offset)  
	            .limit(limit)    
	            .fetch();
	}

	/**
	 * 주어진 키워드로 검색된 리뷰의 개수를 반환합니다.
	 * 
	 * @param keyword 검색 키워드
	 * @return 검색된 리뷰 개수
	 */
	// keyword 검색 조건에 맞는 리뷰의 개수
	public int count(String keyword) {

		BooleanBuilder builder = new BooleanBuilder();
		
	    if (keyword != null && !keyword.trim().isEmpty()) {

	    	builder.or(vwReview.category.contains(keyword))
            	   .or(vwReview.title.contains(keyword))
            	   .or(vwReview.content.contains(keyword))
                   .or(vwReview.name.contains(keyword));
	    }

	    // 검색 조건에 맞는 데이터의 개수
	    return jpaQueryFactory
	            .select(vwReview.count())
	            .from(vwReview)
	            .where(builder)  
	            .fetchOne()
	            .intValue();	// 결과를 int로 변환하여 반환
	}
	
	/**
	 * 주어진 리뷰 고유 식별자(seq)에 해당하는 리뷰 정보를 수정합니다.
	 * 
	 * @param seq 리뷰의 고유 식별자
	 * @param category 수정할 카테고리
	 * @param name 수정할 영양제 이름
	 * @param title 수정할 제목
	 * @param content 수정할 내용
	 */
	// seq가 일치하는 리뷰 수정
	@Transactional
	public void updateReview(Long seq, String category, String name, String title, String content) {
		
//		jpaQueryFactory.update(reviewImage)
//		   			   .where(reviewImage.review_seq.eq(seq))
//		   			   .set(reviewImage.image, image) 
//		   			   .execute();
		
		jpaQueryFactory.update(review)
		   			   .where(review.seq.eq(seq))
		   			   .set(review.category, category) 
	                   .set(review.name, name) 
	                   .set(review.title, title) 
	                   .set(review.content, content)
		               .execute();		
	}

	/**
	 * 주어진 리뷰 고유 식별자(seq)에 해당하는 리뷰와 관련된 리뷰 이미지를 삭제합니다.
	 * 
	 * @param seq 리뷰의 고유 식별자
	 */
	// seq가 일치하는 리뷰 삭제
	@Transactional
	public void deleteReviewBySeq(Long seq) {

		jpaQueryFactory.delete(reviewImage)
		               .where(reviewImage.review.seq.eq(seq))
		               .execute();
				
		jpaQueryFactory.delete(review)
					   .where(review.seq.eq(seq))
					   .execute();
	}
}
