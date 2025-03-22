package com.test.nutri.model;

import lombok.Data;

/**
 * MapDTO
 * MapDTO 클래스는 약국 정보를 담는 데이터 전송 객체(Data Transfer Object)로, 
 * 약국의 기본 정보와 운영 시간 관련 세부 정보를 저장합니다. 
 * 이 클래스는 약국 API로부터 받은 데이터를 매핑하여 사용하거나, 
 * 프런트엔드로 데이터를 전달하는 데 활용됩니다.
 * @author Yujeong Choi
 */
@Data
public class MapDTO {
	
	private String hpid; //seq
	
    private String name;
    private String address;
    private String tel;
    private double latitude = 0;  // 기본값
    private double longitude = 0; // 기본값
    private String openTime;      // 영업 시작 시간
    private String closeTime; 	  // 영업 종료 시간
    private boolean open;         // 영업 여부
    private String etc; 	      //비고
    
    private String dutyTime1s; //월요일 start 시간
    private String dutyTime1c; //월요일 close 시간
    private String dutyTime2s; //화요일 start 시간
    private String dutyTime2c; //화요일 close 시간
    private String dutyTime3s; //수요일 start 시간
    private String dutyTime3c; //수요일 close 시간
    private String dutyTime4s; //목요일 start 시간
    private String dutyTime4c; //목요일 close 시간
    private String dutyTime5s; //금요일 start 시간
    private String dutyTime5c; //금요일 close 시간
    private String dutyTime6s; //토요일 start 시간
    private String dutyTime6c; //토요일 close 시간
    private String dutyTime7s; //일요일 start 시간
    private String dutyTime7c; //일요일 close 시간
    private String dutyTime8s; //공휴일 start 시간
    private String dutyTime8c; //공휴일 close 시간 
    
}
