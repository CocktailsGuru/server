ALTER TABLE `cocktail_dev`.`coctail_ingred_alco` 
DROP COLUMN `flags`,
DROP COLUMN `numKcal`,
DROP COLUMN `dataCol`,
DROP COLUMN `tasteFK`,
DROP COLUMN `websiteUrl`,
DROP COLUMN `videoUrl`,
DROP COLUMN `nameModif`;



ALTER TABLE `cocktail_dev`.`coctail_ingred_alco_non`
DROP COLUMN `flags`,
DROP COLUMN `dataCol`,
DROP COLUMN `numKcal`,
DROP COLUMN `tasteFK`,
DROP COLUMN `websiteUrl`,
DROP COLUMN `videoUrl`,
DROP COLUMN `preparation`,
DROP COLUMN `nameModif`;