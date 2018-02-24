package com.cocktailsguru.app.cocktail.dto;

import lombok.Data;

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
    private List<Long> ingredientList;
    private List<Long> similarCocktailList;
    private int numOfFavorite;
    private double calculatedRating;
    private int numRating1;
    private int numRating2;
    private int numRating3;
    private int numRating4;
    private int numRating5;
    private double alcoholVolume;
    private int numPictures;
    private int numComments;
    private int numShowed;

    public CocktailDetailDto() {
    }
}
