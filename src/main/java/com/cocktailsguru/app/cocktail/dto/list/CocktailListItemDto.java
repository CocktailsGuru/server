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
    private String calculatedRating;
    private int alcoVolume;
}
