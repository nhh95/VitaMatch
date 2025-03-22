package com.test.nutri.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Review 클래스는 review 테이블과 매핑되는 엔티티로, 
 * 사용자가 작성한 리뷰 정보를 저장합니다. 
 * 이 클래스는 ReviewImage 엔티티와 연관 관계를 가집니다. 
 * 리뷰에는 제목, 카테고리, 내용 및 작성자의 정보가 포함됩니다.
 * 
 * @author jiyun
 */
@Entity
@Getter
@ToString
@Builder
@Table(name="review")
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	/**
     * 리뷰 일련번호 (Primary Key)
     * 데이터베이스에서 자동으로 생성되는 기본키입니다.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//데이터베이스 기본키 자동 생성
	private long seq;
	
	/**
     * 리뷰를 작성한 사용자의 식별자
     * 사용자의 'Member' 테이블의 'seq'와 연관되며, 해당 사용자와 리뷰를 연결합니다.
     */
	private long member_seq;
	
	/**
     * 리뷰가 작성된 날짜 및 시간
     * 리뷰가 등록되거나 수정될 때 자동으로 갱신됩니다.
     */
	@UpdateTimestamp
	@Column(name = "regDate")
	private LocalDateTime regDate;
	
	/**
     * 리뷰의 제목
     * 사용자가 작성한 리뷰의 제목을 저장합니다.
     */
	private String title;
	
	/**
     * 리뷰의 카테고리
     * 사용자가 선택한 카테고리 정보가 저장됩니다.
     */
	private String category;
	
	 /**
     * 리뷰의 대상 영양제 이름
     * 사용자가 작성한 리뷰가 특정 영양제에 대한 것임을 나타냅니다.
     */
	private String name;
	
	/**
     * 리뷰의 내용
     * 사용자가 작성한 리뷰의 본문 내용이 포함됩니다.
     */
	private String content;
	
	/**
     * 리뷰에 첨부된 이미지들의 리스트
     * 리뷰와 관련된 이미지를 여러 개 첨부할 수 있으며, 
     * 'ReviewImage' 엔티티와는 'review' 필드를 통해 매핑됩니다.	
     */
	@OneToMany(mappedBy = "review")  // ReviewImage에서 'review' 필드와 매핑
    private List<ReviewImage> reviewImages;
}
