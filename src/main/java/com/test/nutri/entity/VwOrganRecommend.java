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
@Table(name="vwOrganRecommend") //테이블명 동일
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VwOrganRecommend {

	@Id
	private Long seq;
	
	private String name;
	
	private Integer organSeq;
	private Integer ingredientSeq;
	private String ingredientName;
	private String functionalContent;
	
}
