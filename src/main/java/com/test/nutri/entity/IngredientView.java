package com.test.nutri.entity;

import com.test.nutri.model.IngredientViewDTO;

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
 * Entity 클래스: IngredientView
 * 
 * 이 클래스는 데이터베이스의 "ingredient_view" 테이블과 매핑됩니다.
 * 주로 영양소 관련 데이터를 관리하며, 
 * DTO(Data Transfer Object)로 변환하기 위한 메서드를 제공합니다.
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "ingredient_view") 
public class IngredientView {

    @Id
    @Column(name = "content_seq") // View에서 Primary Key 역할을 하는 컬럼
    private int contentSeq;

    @Column(name = "ingredient_seq")
    private int ingredientSeq;

    @Column(name = "ingredient_name")
    private String ingredientName;

    @Column(name = "ingredient_category")
    private int ingredientCategory;

    @Column(name = "functionalContent")
    private String functionalContent;

    @Column(name = "dailyIntake")
    private String dailyIntake;

    @Column(name = "precautionsForIngestion")
    private String precautionsForIngestion;

    
    
    /**
     * Entity를 DTO로 변환하는 메서드
     * 
     * @return IngredientViewDTO 객체로 변환된 데이터
     */
    public IngredientViewDTO toDTO() {
        return new IngredientViewDTO(
            ingredientSeq,
            ingredientName,
            ingredientCategory,
            contentSeq,
            functionalContent,
            dailyIntake,
            precautionsForIngestion
        );
    }
}
