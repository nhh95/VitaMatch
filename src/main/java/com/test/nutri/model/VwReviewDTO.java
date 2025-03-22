package com.test.nutri.model;

import java.time.LocalDateTime;

import com.test.nutri.entity.VwReview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * VwReviewDTO 클래스는 리뷰 정보를 전달하는 데이터 전송 객체(DTO)입니다. 
 * 이 클래스는 사용자에게 보여줄 리뷰 데이터를 캡슐화하여 반환하는 역할을 합니다. 
 * DTO는 엔티티인 `VwReview` 객체와 상호 변환이 가능하며, 
 * 클라이언트와 서버 간에 데이터를 안전하고 효율적으로 전송할 수 있게 합니다.
 * 
 * @author jiyun
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VwReviewDTO {
	
	/**
	 * 리뷰 일련번호.
	 * 각 리뷰를 고유하게 식별할 수 있는 기본 키입니다.
	 */
	private long seq;
	
	/**
	 * 리뷰 작성자의 닉네임.
	 * 리뷰를 작성한 사용자의 이름이 표시됩니다.
	 */
	private String nickname;
	
	/**
	 * 리뷰 작성 일자.
	 * 리뷰가 작성된 날짜와 시간이 기록됩니다.
	 */
	private LocalDateTime createDate;
	
	/**
	 * 리뷰 제목.
	 * 사용자가 작성한 리뷰의 제목이 저장됩니다.
	 */
	private String title;
	
	/**
	 * 리뷰 카테고리.
	 * 리뷰가 속하는 카테고리 정보가 포함됩니다.
	 */
	private String category;
	
	/**
	 * 리뷰 대상 영양제 이름.
	 * 리뷰가 작성된 영양제의 이름이 포함됩니다.
	 */
	private String name;
	
	/**
	 * 리뷰 내용.
	 * 사용자가 작성한 리뷰의 본문 내용이 저장됩니다.
	 */
	private String content;
	
	/**
	 * 리뷰 이미지.
	 * 리뷰와 관련된 이미지 경로 또는 파일명이 저장됩니다.
	 */
	private String image;
	
	/**
	 * DTO 객체를 엔티티 객체로 변환하는 메서드.
	 * `VwReviewDTO`를 `VwReview` 엔티티로 변환하여 데이터베이스와 연관된 작업을 수행할 수 있도록 합니다.
	 * 
	 * @param dto - 변환할 DTO 객체
	 * @return `VwReview` 엔티티 객체
	 */
	public static VwReview toEntity(VwReviewDTO dto) {
		
		return VwReview.builder()
				.seq(dto.getSeq())
				.nickname(dto.getNickname())
				.createDate(dto.getCreateDate())
				.title(dto.getTitle())
				.category(dto.getCategory())
				.name(dto.getName())
				.content(dto.getContent())
				.image(dto.getImage())
				.build();
	}
}
