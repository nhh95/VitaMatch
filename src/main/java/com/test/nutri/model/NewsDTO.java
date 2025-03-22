package com.test.nutri.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.test.nutri.entity.News;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NewsDTO는 뉴스 데이터를 전달하기 위한 데이터 전송 객체입니다.
 * 
 * @author chimy2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {

    /** 뉴스 제목 */
	private String title;

    /** 원본 뉴스 링크 */
	private String originallink;

    /** 뉴스 링크 */
	private String link;

    /** 뉴스 설명 */
	private String description;

    /** 뉴스 발행일 */
	private LocalDateTime pubDate;

    /**
     * 발행일을 문자열로 받아 LocalDateTime으로 변환하여 설정합니다.
     * 
     * @param pubDate 문자열 형식의 발행일 (예: "Mon, 01 Jan 2024 12:00:00 +0900")
     */
    public void setPubDate(String pubDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", java.util.Locale.ENGLISH);
        this.pubDate = LocalDateTime.parse(pubDate, formatter);
    }

    /**
     * DTO 데이터를 News 엔티티로 변환합니다.
     * 
     * @return News 엔티티 객체
     */
	public News toEntity() {
		return News.builder()
				.title(title)
				.originalLink(originallink)
				.link(link)
				.description(description)
				.regDate(pubDate)
				.build();
	}
}
