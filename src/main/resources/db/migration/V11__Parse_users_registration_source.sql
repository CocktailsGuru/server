ALTER TABLE `cocktail_dev`.`coctail_user` DROP COLUMN `lastGps`;
ALTER TABLE `cocktail_dev`.`coctail_user` DROP COLUMN `numSessions`;
ALTER TABLE `cocktail_dev`.`coctail_user` DROP COLUMN `userAge`;
ALTER TABLE `cocktail_dev`.`coctail_user` DROP COLUMN `numCocktailsPrep`;
ALTER TABLE `cocktail_dev`.`coctail_user` DROP COLUMN `numCocktailsPurch`;
ALTER TABLE `cocktail_dev`.`coctail_user` DROP COLUMN `numPoints`;
ALTER TABLE `cocktail_dev`.`coctail_user` DROP COLUMN `userFBAgeGroup`;
ALTER TABLE `cocktail_dev`.`coctail_user` DROP COLUMN `userFBCountry`;
ALTER TABLE `cocktail_dev`.`coctail_user` DROP COLUMN `userEmail`;
ALTER TABLE `cocktail_dev`.`coctail_user` DROP COLUMN `userPushId`;


ALTER TABLE `cocktail_dev`.`coctail_user` ADD COLUMN `registrationType` VARCHAR(45) NULL AFTER `userFBLanguage`;

UPDATE `coctail_user` SET `registrationType`= CASE WHEN char_length(`coctail_user`.`userID`) = 21 THEN "Google" ELSE "FB" END;