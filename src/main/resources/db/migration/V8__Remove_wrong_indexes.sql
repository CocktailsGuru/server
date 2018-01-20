INSERT INTO `cocktail_dev`.`coctail_method` (`id`, `name`, `desc`) VALUES ('12', 'Unknown', '');

UPDATE coctail SET methodFK = 12 WHERE methodFK = -1;

ALTER TABLE `cocktail_dev`.`coctail` DROP COLUMN `numRating`;
