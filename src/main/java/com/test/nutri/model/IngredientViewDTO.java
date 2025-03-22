package com.test.nutri.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IngredientViewDTO {
    private int ingredientSeq;
    private String ingredientName;
    private int ingredientCategory;
    private int contentSeq;
    private String functionalContent;
    private String dailyIntake;
    private String precautionsForIngestion;
}