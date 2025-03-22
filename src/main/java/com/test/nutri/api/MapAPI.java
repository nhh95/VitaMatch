package com.test.nutri.api;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.test.nutri.model.MapDTO;

import lombok.RequiredArgsConstructor;

/** 
 * MapAPI
 * MapAPI 클래스는 Kakao 지도 API와 공공 데이터 포털의 약국 정보 API를 통합적으로 관리하기 위한 구성 요소입니다. 
 * 이 클래스는 약국 정보 조회를 위한 URL 생성 및 뷰에서 사용할 Kakao 지도 API 키를 제공합니다. 
 * Spring Framework의 @Component로 정의되어 IoC 컨테이너에서 관리됩니다.
 * @author YuJeong Choi
 */
@Component
@RequiredArgsConstructor
public class MapAPI {
    
	/**
	 * Kakao 지도 API의 애플리케이션 키를 저장합니다.
	 * 타입: String
	 */
    @Value("${api-key.kakaomap.appkey}")
    private String kakaomap;
    
    /**
     * 공공 데이터 포털의 약국 API 서비스 키를 저장합니다.
     * 타입: String
     */
    @Value("${api-key.pharmacy.appkey}")
    private String pharmacyKey;

    /**
	 * Kakao 지도 API 키를 뷰에 전달합니다.
     * @param model 뷰로 데이터를 전달하기 위한 Spring MVC의 모델 객체
     * @return String - 반환할 뷰의 이름 ("page/map")
     * 로직: model에 kakaoApiKey 속성을 추가하고 뷰 이름을 반환합니다.
     */
    public String getMap(Model model) {
        model.addAttribute("kakaoApiKey", kakaomap);
        return "page/map"; 
    }
    
    /**
     * 약국 API의 서비스 키를 반환합니다.
     * @return String - 약국 API 서비스 키
     * 로직: 설정 파일에서 로드된 pharmacyKey를 반환합니다.
     */
    public String getPharmacyApiKey() {
        return pharmacyKey;
    }
    
    /**
     * 약국 목록 조회 URL을 생성합니다.
     * @param sido 시/도 이름
     * @param gugun 구/군 이름
     * @param page 페이지 번호
     * @param keyword 검색 키워드 (옵션)
     * @return String - 생성된 URL
     * 로직: 
     * 시/도, 구/군, 키워드를 UTF-8로 인코딩합니다.
     * 기본 URL과 파라미터를 조합하여 최종 URL을 생성합니다.
     * 생성된 URL을 반환합니다.
     */
    public String pharmacyListUrl(String sido, String gugun, int page, String keyword) {
    	
    	 try {
    	        String baseUrl = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire";
    	        String apiKey = pharmacyKey;
    	        
    	        // URL 인코딩
    	        String encodedSido = URLEncoder.encode(sido, StandardCharsets.UTF_8);
    	        String encodedGugun = URLEncoder.encode(gugun, StandardCharsets.UTF_8);
    	        String encodedKeyword = keyword != null ? URLEncoder.encode(keyword, StandardCharsets.UTF_8) : "";

    	        // URL 생성
    	        String url = String.format(
    	            "%s?serviceKey=%s&Q0=%s&Q1=%s&QN=%s&pageNo=%d&numOfRows=1000000",
    	            baseUrl, apiKey, encodedSido, encodedGugun, encodedKeyword, page
    	        );
    	        
    	        System.out.println("페이징 테스트 " + url);
    	        
    	        return url;
    	        
    	    } catch (Exception e) {
    	        throw new RuntimeException("URL 생성 중 오류 발생", e);
    	    }

    }

    /**
     * 개별 약국 정보를 조회하기 위한 URL을 생성합니다.
     * @param hpid 약국 ID
     * @return String - 생성된 URL
     * 로직: 약국 ID(hpid)를 포함한 URL을 생성하여 반환합니다.
     */
	public String pharmacyInfoUrl(String hpid) {
		
		String url = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyBassInfoInqire"
				   + "?serviceKey=" + pharmacyKey
				   + "&HPID=" + hpid;
		
		return url;
	} 
     
}

