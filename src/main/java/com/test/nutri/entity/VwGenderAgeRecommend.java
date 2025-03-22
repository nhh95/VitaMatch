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
@Table(name="vwGenderAgeRecommend") //테이블명 동일
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VwGenderAgeRecommend {

	@Id
	private Long seq;
	
	private String gender;
	private String age;
	
	private Integer genderAgeSeq;
	private Integer ingredientSeq;
	private String ingredientName;
	private String functionalContent;
	
}
