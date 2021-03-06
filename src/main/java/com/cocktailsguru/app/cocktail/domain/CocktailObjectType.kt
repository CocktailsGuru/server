package com.cocktailsguru.app.cocktail.domain

/**
 * See `coctail_object_type` table
 */
enum class CocktailObjectType {
    NONE,
    COCKTAIL,
    INGREDIENT,
    USER_PICTURE,
    USER_COMMENT_INGREDIENT,
    USER_COMMENT_PICTURE,
    USER_COMMENT_COCKTAIL
}