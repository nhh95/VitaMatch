package com.test.nutri.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.nutri.entity.VwDailyRecommend;
import com.test.nutri.entity.VwGenderAgeRecommend;
import com.test.nutri.entity.VwHealthRecommend;
import com.test.nutri.entity.VwOrganRecommend;
import com.test.nutri.repository.DailyRecommendRepository;
import com.test.nutri.repository.GenderAgeRecommendRepository;
import com.test.nutri.repository.HealthRecommendRepository;
import com.test.nutri.repository.OrganRecommendRepository;

import lombok.RequiredArgsConstructor;

/**
 * RecommendController
 * 사용자 맞춤형 추천 데이터를 처리하는 컨트롤러 클래스입니다.
 * 이 클래스는 사용자가 제공한 파라미터에 따라 다양한 추천 정보를 조회하고,
 * 해당 데이터를 뷰로 전달하여 화면에 표시합니다.
 * @author Yujin Kim
 */
@Controller
@RequiredArgsConstructor
public class RecommendController {
	
	private final GenderAgeRecommendRepository genderAgeRecommendRepository;
	private final HealthRecommendRepository healthRecommendRepository;
	private final OrganRecommendRepository organRecommendRepository;
	private final DailyRecommendRepository dailyRecommendRepository;
	
	/**
	 * 영양제 설문 페이지를 호출합니다.
	 * @param model
	 * @return
	 */
	@GetMapping("/survey")
	public String survey(Model model) {
		
		return "page/survey";
	}
	
	/**
	 * recommend 메서드는 사용자가 제공한 성별, 나이대, 건강 상태, 주요 장기, 일상 생활과 같은 파라미터를 바탕으로
	 * 맞춤형 추천 데이터를 조회합니다.
	 * @param gender
	 * @param age
	 * @param healthSeq
	 * @param organSeq
	 * @param dailySeq
	 * @param model
	 * @return
	 */
	@GetMapping("/recommend")
	public String recommend(@RequestParam(value = "gender", required = false)String gender
							, @RequestParam(value = "age", required = false) String age
							, @RequestParam(value = "healthSeq", required = false)String healthSeq
							, @RequestParam(value = "organSeq", required = false)String organSeq
							, @RequestParam(value = "dailySeq", required = false)String dailySeq, Model model) {
		
		List<VwGenderAgeRecommend> galist = new ArrayList<>();
		List<VwHealthRecommend> hlist = new ArrayList<>();
		List<VwOrganRecommend> olist = new ArrayList<>();
		List<VwDailyRecommend> dlist = new ArrayList<>();
		
		if (gender != null & age != null ) {
			galist = genderAgeRecommendRepository.findAll(gender, age + "0");
			System.out.println("gender: " + gender + "age: " + age);
			System.out.println(galist);
		} else {
			System.out.println("성별과 나이대를 선택해주세요.");
		}
		
		if (healthSeq != null || organSeq != null || dailySeq != null) {
			hlist = healthRecommendRepository.findAll(healthSeq);
			System.out.println("health: " + healthSeq);
			System.out.println(hlist);
			
			olist = organRecommendRepository.findAll(organSeq);
			System.out.println("organ: " + organSeq);
			System.out.println(olist);
			
			dlist = dailyRecommendRepository.findAll(dailySeq);
			System.out.println("daily: " + dailySeq);
			System.out.println(dlist);
		
		} else if (healthSeq == null) {
			System.out.println("건강 검진 선택해주세요.");
		} else if (organSeq == null) {
			System.out.println("주요 장기 선택해주세요.");
		} else if (dailySeq == null) {
			System.out.println("일상 생활 선택해주세요.");
		}
		
		
		model.addAttribute("galist", galist);
		model.addAttribute("hlist", hlist);
		model.addAttribute("olist", olist);
		model.addAttribute("dlist", dlist);
		
		return "page/recommend";
	}
	
}
