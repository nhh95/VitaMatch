package com.test.nutri.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name="vwHealthRecommend") //테이블명 동일
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VwHealthRecommend {

	@Id
	private Long seq;
	
	private String name;
	
	private Integer healthSeq;
	private Integer ingredientSeq;
	private String ingredientName;
	private String functionalContent;
	
}
