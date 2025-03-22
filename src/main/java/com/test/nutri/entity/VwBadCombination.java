package com.test.nutri.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * VwBadCombination
 * 나쁜 영양제 성분 조합 정보를 저장하는 엔티티
 * 성분명, 이유, 링크, 선택된 성분명 등 다양한 정보를 포함
 * Lombok과 anotation으로 코드 간결성을 높임
 * @author bohwa Jang
 * 
 */
@Entity
@Getter
@ToString
@Table(name="vwBadCombination") //테이블명 동일
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VwBadCombination {

	@Id
	private Long seq;
	
	private String bad;
	private String reason;
	private String link;
	private String ingredientName;
	private String name;
	private String functionalContent;
	
	@Column(name = "ingredient_seq")
	private String ingredientSeq;
}
