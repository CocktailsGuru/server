package com.cocktailsguru.app.cocktail.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class CocktailDetailDto {
    private Long id;
    private String name;
    private int totalVolume;
    private int alcoVolume;
    private int nonAlcoVolume;
    private String instructions;
    private String garnish;
    private String description;
    private String imageName;
    private CocktailGlassDto glass;
    private CocktailMethodDto method;
    private List<Long> alcoIngredIdList;
    private List<Long> nonAlcoIngredIdList;
    private List<Long> similarCocktailIdList;

    public CocktailDetailDto() {
    }
}
