package com.test.nutri.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.nutri.api.MapAPI;
import com.test.nutri.model.MapDTO;
import com.test.nutri.service.MapService;

import lombok.RequiredArgsConstructor;

/**
 * MapController
 * MapController 클래스는 약국 정보와 지도 관련 요청을 처리하는 Spring MVC 컨트롤러입니다. 
 * 이 컨트롤러는 사용자가 지정한 조건에 따라 약국 목록과 개별 약국 정보를 조회하고, 
 * Kakao 지도 화면을 제공하는 역할을 합니다.
 * @author YuJeong Choi
 */
@Controller
@RequiredArgsConstructor
public class MapController {
	
	/**
	 * 지도 및 약국 API 요청을 처리하는 클래스
	 */
    private final MapAPI mapAPI;
    
    /**
     * 약국 데이터를 서비스 레이어에서 처리하는 클래스
     */
    private final MapService mapService;

    
    /**
     * Kakao 지도 화면을 반환합니다.
     * @param model 뷰로 데이터를 전달하기 위한 Spring MVC의 모델 객체
     * @return String - 반환할 뷰 이름 ("page/map")
     * 로직: MapAPI의 getMap 메서드를 호출하여 뷰 이름을 반환합니다.
     */
    @GetMapping("/map")
    public String map(Model model) {
        return mapAPI.getMap(model); 
    }

    /**
     * 약국 목록을 조회합니다.
     * @param sido - 시/도 이름
     * @param gugun - 구/군 이름
     * @param page - 페이지 번호 (기본값: 1)
     * @param keyword - 검색 키워드 (옵션)
     * @return List<MapDTO> - 약국 정보 목록
     * 로직:
     * MapAPI의 pharmacyListUrl 메서드를 호출하여 약국 API 요청 URL을 생성합니다.
     * 생성된 URL을 MapService의 getPharmacyList 메서드로 전달하여 데이터를 조회합니다.
     */
    @GetMapping("/pharmacy/list")
    @ResponseBody
    public List<MapDTO> getPharmacy(
        @RequestParam(name = "sido") String sido,
        @RequestParam(name = "gugun") String gugun,
        @RequestParam(name = "page", defaultValue = "1") int page,
        @RequestParam(name = "keyword", required = false) String keyword
    ) {
        String url = mapAPI.pharmacyListUrl(sido, gugun, page, keyword);
        return mapService.getPharmacyList(url);
    }
    
    /**
     * 개별 약국 정보를 조회합니다.
     * @param hpid - 조회할 약국의 고유 ID
     * @return List<MapDTO> - 약국 상세 정보
     * 로직:
     * MapAPI의 pharmacyInfoUrl 메서드를 호출하여 개별 약국 API 요청 URL을 생성합니다.
     * 생성된 URL을 MapService의 getPharmacyList 메서드로 전달하여 데이터를 조회합니다.
     */
    @GetMapping("/pharmacy/info") 
    @ResponseBody
    public List<MapDTO> getPharmacyInfo (@RequestParam(name="hpid") String hpid) {
    	
    	String url = mapAPI.pharmacyInfoUrl(hpid);
    	
    	//List말고 MapDTO로 나누고 싶음... 
    	List<MapDTO> list = mapService.getPharmacyList(url);
    		
    	return list;
    }

}
