DROP TABLE `cocktail_dev`.`coctail_ingred_nutririon`;
DROP TABLE `cocktail_dev`.`coctail_user_coctail_prepared`;
DROP TABLE `cocktail_dev`.`coctail_user_coctail_purchased`;


ALTER TABLE `cocktail_dev`.`coctail` DROP COLUMN `kcalVolume`;
ALTER TABLE `cocktail_dev`.`coctail` DROP COLUMN `dataCol`;
ALTER TABLE `cocktail_dev`.`coctail` DROP COLUMN `groupType`;
ALTER TABLE `cocktail_dev`.`coctail` DROP COLUMN `groupType2`;
ALTER TABLE `cocktail_dev`.`coctail` DROP COLUMN `groupType3`;
ALTER TABLE `cocktail_dev`.`coctail` DROP COLUMN `numPurchased`;

