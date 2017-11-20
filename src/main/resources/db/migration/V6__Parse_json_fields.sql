CREATE TABLE `cocktail_dev`.`coctail_alco_ingredients` (
  `coctail_id` INT(10) NOT NULL,
  `ingred_alco_id` INT(10) NOT NULL,
  `volume` VARCHAR(10) NULL);


CREATE TABLE `cocktail_dev`.`coctail_non_alco_ingredients` (
  `coctail_id` INT(10) NOT NULL,
  `ingred_non_alco_id` INT(10) NOT NULL,
  `volume` VARCHAR(10) NULL);



DROP PROCEDURE IF EXISTS ingredientsParse;
DELIMITER //
CREATE PROCEDURE ingredientsParse(from_index INT, to_index INT)
BEGIN
	DECLARE coctailId int(10);
	DECLARE ingredient varchar(2000);
    DECLARE curr_ingred varchar(1000);
    DECLARE ingredIndex INT;
    DECLARE done INT DEFAULT 0;
    DECLARE ingredSize INT;
    DECLARE ingredCur CURSOR FOR SELECT id, ingredients from coctail WHERE id BETWEEN from_index AND to_index;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN ingredCur;

    REPEAT
		FETCH ingredCur INTO coctailId, ingredient;
        IF NOT done THEN
			SET ingredSize = JSON_LENGTH(ingredient);
            SET ingredIndex = 0;
			ingred_loop: LOOP
				SET curr_ingred = JSON_EXTRACT(ingredient, CONCAT("$[", ingredIndex, "]"));


                IF curr_ingred->>"$.id" IS NOT NULL THEN
					IF curr_ingred->>"$.id" < 1000 THEN
						INSERT INTO coctail_alco_ingredients (coctail_id, ingred_alco_id, volume) VALUES (coctailId, curr_ingred->>"$.id", IFNULL(NULLIF(curr_ingred->>"$.volume", ''), 0));
					ELSE
						INSERT INTO coctail_non_alco_ingredients (coctail_id, ingred_non_alco_id, volume) VALUES (coctailId, curr_ingred->>"$.id", IFNULL(NULLIF(curr_ingred->>"$.volume", ''), 0));
					END IF;
				END IF;
                SET ingredIndex = ingredIndex+1;
                IF ingredIndex = ingredSize THEN
					LEAVE ingred_loop;
				END IF;
			END LOOP ingred_loop;
		END IF;
	UNTIL done END REPEAT;
    CLOSE ingredCur;
END //

DELIMITER ;

CALL ingredientsParse(0, 4999);
CALL ingredientsParse(5000, 9999);
CALL ingredientsParse(10000, 14999);
CALL ingredientsParse(15000, 19999);


ALTER TABLE coctail DROP coctail.ingredients;
ALTER TABLE coctail DROP coctail.ingredientsIds;
ALTER TABLE coctail DROP coctail.numIngredTotal;
ALTER TABLE coctail DROP coctail.numIngredAlko;
ALTER TABLE coctail DROP coctail.numIngredAlkoNon;


CREATE TABLE `cocktail_dev`.`coctail_similar` (
  `cocktail_one` INT NOT NULL,
  `cocktail_two` INT NOT NULL);
);

DROP PROCEDURE IF EXISTS similarCocktailParse;
DELIMITER //
CREATE PROCEDURE similarCocktailParse(from_index INT, to_index INT)
BEGIN
	DECLARE coctailId int(10);
	DECLARE simillarDrink varchar(2000);
    DECLARE curr_simillar varchar(1000);
    DECLARE drinkIndex INT;
    DECLARE done INT DEFAULT 0;
    DECLARE drinkSize INT;
    DECLARE coctailCur CURSOR FOR SELECT id, simillarDrinks from coctail WHERE id BETWEEN from_index AND to_index;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN coctailCur;

    REPEAT
		FETCH coctailCur INTO coctailId, simillarDrink;
        IF NOT done THEN
			SET drinkSize = JSON_LENGTH(simillarDrink);
            IF drinkSize > 0 THEN
				SET drinkIndex = 0;
				drink_loop: LOOP
					SET curr_simillar = JSON_EXTRACT(simillarDrink, CONCAT("$[", drinkIndex, "]"));
					INSERT INTO coctail_similar (cocktail_one, cocktail_two) VALUES(coctailId, curr_simillar);
					SET drinkIndex = drinkIndex+1;
					IF drinkIndex = drinkSize THEN
						LEAVE drink_loop;
					END IF;
				END LOOP drink_loop;
			END IF;
		END IF;
	UNTIL done END REPEAT;
    CLOSE coctailCur;
END //

DELIMITER ;

CALL similarCocktailParse(0,50000);

ALTER TABLE coctail DROP coctail.simillarDrinks;
