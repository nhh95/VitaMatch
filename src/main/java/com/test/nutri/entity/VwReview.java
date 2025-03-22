package com.test.nutri.entity;

import java.time.LocalDateTime;

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
 * VwReview 클래스는 뷰(VW)에서 가져온 리뷰 정보를 저장하는 엔티티 클래스입니다. 
 * 이 클래스는 데이터베이스의 `vwReview` 테이블과 매핑되며, 사용자가 작성한 리뷰 정보를 포함합니다. 
 * 각 리뷰는 사용자의 닉네임, 작성일자, 제목, 카테고리, 리뷰 대상 영양제 이름, 내용 및 이미지 등의 정보를 포함합니다.
 * 
 * 이 클래스는 뷰로부터 데이터를 조회하는데 사용되며, 실제 'Review' 테이블과의 관계를 바탕으로 리뷰 정보를 제공합니다.
 * 
 * @author jiyun
 */
@Entity
@Getter
@ToString
@Builder
@Table(name="vwReview")
@AllArgsConstructor
@NoArgsConstructor
public class VwReview {
	
	/**
	 * 리뷰 일련번호
	 * 각 리뷰를 고유하게 식별할 수 있는 기본 키입니다.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//데이터베이스 기본키 자동 생성
	private long seq;
	
	/**
	 * 리뷰 작성자의 닉네임
	 * 리뷰를 작성한 사용자의 이름이 표시됩니다.
	 */
	private String nickname;
	
	/**
	 * 리뷰 작성 일자
	 * 리뷰가 작성된 날짜와 시간이 기록됩니다.
	 */
	private LocalDateTime createDate;
	
	/**
	 * 리뷰 제목
	 * 사용자가 작성한 리뷰의 제목이 저장됩니다.
	 */
	private String title;
	
	/**
	 * 리뷰 카테고리
	 * 리뷰가 속하는 카테고리 정보가 포함됩니다.
	 */
	private String category;
	
	/**
	 * 리뷰 대상 영양제 이름
	 * 리뷰가 작성된 영양제의 이름이 포함됩니다.
	 */
	private String name;
	
	/**
	 * 리뷰 내용
	 * 사용자가 작성한 리뷰의 본문 내용이 저장됩니다.
	 */
	private String content;
	
	/**
	 * 리뷰 이미지
	 * 리뷰와 관련된 파일명이 저장됩니다.
	 */
	private String image;

}

