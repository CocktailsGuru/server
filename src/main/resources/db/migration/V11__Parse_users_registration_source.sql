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


DROP PROCEDURE IF EXISTS usersParse;
DELIMITER //
CREATE PROCEDURE usersParse(from_index INT, to_index INT)
BEGIN
	DECLARE userId int(10);
	DECLARE userExternalId VARCHAR(110);
    DECLARE done INT DEFAULT 0;
    DECLARE userCur CURSOR FOR SELECT `coctail_user`.`id`, `coctail_user`.`userID` from `coctail_user` WHERE `coctail_user`.`id` BETWEEN from_index AND to_index;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN userCur;

    REPEAT
		FETCH userCur INTO userId, userExternalId;
        IF NOT done THEN
			IF char_length(userExternalId) = 21 THEN
				UPDATE `coctail_user` SET `coctail_user`.`registrationType` = "Google" WHERE `coctail_user`.`id` = userId;
			ELSE
				UPDATE `coctail_user` SET `coctail_user`.`registrationType` = "FB" WHERE `coctail_user`.`id` = userId;
			END IF;
		END IF;
	UNTIL done END REPEAT;
	CLOSE userCur;
END //

DELIMITER ;



CALL usersParse(0, 5000);
CALL usersParse(5001, 10000);
CALL usersParse(10001, 15000);
CALL usersParse(10001, 15000);
CALL usersParse(15001, 20000);
CALL usersParse(20001, 25000);
CALL usersParse(25001, 30000);
CALL usersParse(30001, 35000);
CALL usersParse(35001, 40000);
CALL usersParse(40001, 45000);
CALL usersParse(45001, 50000);
CALL usersParse(50001, 55000);
CALL usersParse(55001, 60000);