package com.test.nutri.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * VwBadCombinationDTO
 * 나쁜 영양제 성분 조합 정보를 저장하는 DTO
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
public class VwBadCombinationDTO {

	private Long seq;
	private String bad;
	private String reason;
	private String link;
	private String ingredient_seq;
	private String ingredientName;
	private String name;
}
