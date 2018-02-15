package com.cocktailsguru.app.cocktail.dto.list;

import com.cocktailsguru.app.comment.domain.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
public class CocktailListItemDto {
    private BigInteger id;
    private String name;
    private String image;
    private double calculatedRating;
    private int alcoVolume;
    private int numOfFavorite;
    private List<Comment> commentList;
}
