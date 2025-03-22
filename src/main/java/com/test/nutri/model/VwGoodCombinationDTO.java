package com.test.nutri.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * VwGoodCombinationDTO
 * 좋은 영양제 성분 조합 정보를 저장하는 DTO
 * 성분명, 이유, 링크, 선택된 성분명 등 다양한 정보를 포함
 * Lombok과 anotation으로 코드 간결성을 높임
 * @author bohwa Jang
 * 
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VwGoodCombinationDTO {

	private Long seq;
    private String ingredient_seq;
    private String good;
    private String reason;
    private String link;
    private String ingredientName;
	private String name;
	

	
}
