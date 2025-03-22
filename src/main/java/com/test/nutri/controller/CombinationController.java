package com.test.nutri.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.test.nutri.entity.VwBadCombination;
import com.test.nutri.entity.VwGoodCombination;
import com.test.nutri.repository.BadCombinationRepository;
import com.test.nutri.repository.GoodCombinationRepository;

import lombok.RequiredArgsConstructor;
/**
 * CombinationController
 * 영양제 성분의 좋은 조합과 나쁜 조합을 안내하는 페이지 기능을 구현합니다.
 * 영양제 성분 데이터를 불러오고, 클라이언트 요청에 맞춰 조합 정보를 JSON 형식으로 응답합니다.
 * 
 * @author bohwa Jang
 */

@Controller
@RequiredArgsConstructor
public class CombinationController {

	/**
	 * 영양제 성분 조합에 관한 데이터를 처리하는 리포지토리
	 * 데이터베이스에서 좋은 또는 안 좋은 조합에 관련된 정보를 조회하거나 수정하는 작업을 담당
	 * 
	 */
	private final GoodCombinationRepository goodCombinationRepository;
	private final BadCombinationRepository badCombinationRepository;

	
	/**
	 * GET 요청을 보낼 때 호출되는 컨트롤러 메서드
	 * 영양제 성분의 좋은 조합과 나쁜 조합 정보를 불러와서 페이지에 전달하는 역할
	 * 
	 * @param model 영양제 조합 페이지에 영양제 성분 조합과 관련된 정보를 넘기기 위한 Model 객체
	 * @return 영양제 조합 페이지
	 */
	@GetMapping("/combination")
	public String combination(Model model) {

		// 이름만 가져오기 > 재권님이 데이터 넣으면 join해서 가져와야 함
		List<VwGoodCombination> good = goodCombinationRepository.listAll();
		List<VwBadCombination> bad = badCombinationRepository.listAll();

		Map<String, String> ingredientSeqName = new HashMap<>();

		for (int i = 0; i < good.size(); i++) {
			ingredientSeqName.put(good.get(i).getIngredientSeq(), good.get(i).getIngredientName()); // getSeq()로 seq 값을
																									// 가져옴
		}
		for (int i = 0; i < bad.size(); i++) {
			ingredientSeqName.put(bad.get(i).getIngredientSeq(), bad.get(i).getIngredientName()); // getSeq()로 seq 값을
																									// 가져옴
		}

		System.out.println(ingredientSeqName);

		model.addAttribute("ingredientSeqName", ingredientSeqName);

		return "page/combination";
	}

	/**
	 * 클라이언트에서 AJAX로 보낸 요청을 처리
	 * 영양제 성분의 좋은 조합과 나쁜 조합에 대한 데이터를 JSON 형식으로 응답하는 기능
	 * @param requestData 1개의 영양제를 선택했을 때, 관련있는 영양제 성분 정보
	 * @return 영양제 조합 페이지
	 */
	@PostMapping("/combination/ajax")
	// json으로 변경해줄려고 넣음 > ResponseEntity
	public ResponseEntity<Map<String, Object>> combinationGoodBad(@RequestBody Map<String, Object> requestData) {
	    String ingredientSeq = (String) requestData.get("ingredientSeq");
	    String ingredient = (String) requestData.get("ingredient");
	    
		List<VwGoodCombination> good = goodCombinationRepository.findByIngredientSeq(ingredientSeq);
		List<VwBadCombination> bad = badCombinationRepository.findByIngredientSeq(ingredientSeq);
		
		
		Map<String, Object> combination = new HashMap<>();
		combination.put("seq", ingredientSeq);
		combination.put("ingredient", ingredient);
		combination.put("good", good);
		combination.put("bad", bad);
		if (good != null && !good.isEmpty()) {
			if(good.get(0).getFunctionalContent()!= null) {
				combination.put("functionalContent", good.get(0).getFunctionalContent());
			}
		}else if (bad != null && !bad.isEmpty()) {
			if(bad.get(0).getFunctionalContent()!= null) {
				combination.put("functionalContent", bad.get(0).getFunctionalContent());
			}
		}
		return ResponseEntity.ok(combination);
		
	}

}
