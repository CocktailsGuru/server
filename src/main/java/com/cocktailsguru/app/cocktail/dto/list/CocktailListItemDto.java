package com.cocktailsguru.app.cocktail.dto.list;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class CocktailListItemDto {
    private BigInteger id;
    private String name;
    private String image;
    private double calculatedRating;
    private int alcoVolume;
    private int numOfFavorite;
}
