package com.cocktailsguru.app.cocktail.domain.ingredient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "coctail_ingred_alco")
@Data
@NoArgsConstructor
public class AlcoIngredient extends BaseIngredient {

    private Integer voltage;
}
