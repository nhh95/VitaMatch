package com.test.nutri.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.Data;

/**
 * NewsListDTO는 뉴스 목록을 저장하고 전달하기 위한 데이터 전송 객체입니다.
 * 
 * @author chimy2
 */
@Data
public class NewsListDTO {


    /** 뉴스 데이터를 생성한 날짜와 시간 */
	private LocalDateTime lastBuildDate;

    /** 전체 뉴스 데이터 개수 */
	private Long total;

    /** 현재 페이지의 시작 위치 */
	private int start;

    /** 한 페이지에 표시되는 뉴스 개수 */
	private int display;
	
    /** 뉴스 데이터 목록 */
	private List<NewsDTO> items;

    /**
     * 문자열로 제공된 생성일(lastBuildDate)을 LocalDateTime으로 변환하여 저장합니다.
     * 
     * @param lastBuildDate 문자열 형식의 생성일 (예: "Mon, 01 Jan 2024 12:00:00 +0900")
     */
    public void setLastBuildDate(String lastBuildDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", java.util.Locale.ENGLISH);
        this.lastBuildDate = LocalDateTime.parse(lastBuildDate, formatter);
    }
}
