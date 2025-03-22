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
 * News 엔티티는 뉴스 데이터를 데이터베이스에 저장하기 위한 JPA 엔티티 클래스입니다.
 * 
 * @author chimy2
 */
@Entity
@Getter
@ToString
@Builder
@Table(name = "news")
@AllArgsConstructor
@NoArgsConstructor
public class News {

    /** 뉴스의 고유 식별자 (Primary Key) */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

    /** 뉴스 제목 */
	private String title;

    /** 뉴스 링크 (naver news URL) */
	private String link;

    /** 뉴스의 원본 링크 */
	private String originalLink;

    /** 뉴스 요약 설명 */
	private String description;

    /** 뉴스 등록일 */
	private LocalDateTime regDate;
}