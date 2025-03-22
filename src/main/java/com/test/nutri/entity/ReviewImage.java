package com.test.nutri.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ReviewImage 클래스는 review_image 테이블과 매핑되는 엔티티로, 
 * 사용자가 작성한 리뷰에 첨부된 이미지 정보를 저장합니다. 
 * 각 이미지는 특정 리뷰에 속하게 되며, 리뷰와의 관계를 통해 연결됩니다.
 * 
 * 이 클래스는 'Review' 엔티티와 다대일(Many-to-One) 관계를 가지며, 
 * 각 리뷰에 여러 개의 이미지를 첨부할 수 있습니다.
 * 
 * @author jiyun
 */
@Entity
@Getter
@ToString
@Builder
@Table(name="reviewImage")
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImage {

	/**
     * 이미지 일련번호 (Primary Key)
     * 데이터베이스에서 자동으로 생성되는 기본 키입니다.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//데이터베이스 기본키 자동 생성
	private long seq;

	/**
     * 이 이미지가 속한 리뷰
     * 해당 리뷰를 식별하는 외래키로, `Review` 엔티티와 다대일 관계로 연결됩니다.
     * 한 리뷰에는 여러 이미지가 첨부될 수 있습니다.
     */
	@ManyToOne
	@JoinColumn(name = "review_seq")
	private Review review;
	
	/**
     * 이미지 파일 경로
     * 리뷰에 첨부된 이미지의 파일명을 저장합니다. 
     * 이는 실제 이미지를 식별할 수 있는 이름을 나타냅니다.
     */
	private String image;
}
