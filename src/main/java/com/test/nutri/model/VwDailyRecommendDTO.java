package com.test.nutri.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * VwDailyRecommendDTO
 * 일상생활 관련 설문 항목과 영양제 정보를 저장하는 DTO
 * 설문 항목, 영양제명, 영양제 효능등 다양한 정보를 포함
 * Lombok과 anotation으로 코드 간결성을 높임
 * @author Yujin Kim
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VwDailyRecommendDTO {

	private Long seq;
	private Integer dailySeq;
	private Integer ingredientSeq;
	private String ingredientName;
	private String functionalContent;
	
}
