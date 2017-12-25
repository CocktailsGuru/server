package com.cocktailsguru.app.cocktail.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CocktailMethodDto {
    private Long id;
    private String name;
    private String description;
}