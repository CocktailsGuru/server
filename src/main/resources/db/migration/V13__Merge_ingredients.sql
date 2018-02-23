DROP TABLE IF EXISTS `coctail_ingred_nutririon`;

DROP TABLE IF EXISTS `ingredient`;

CREATE TABLE `ingredient`
(
  `id`          INT UNSIGNED                   NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name`        VARCHAR(200) DEFAULT ''        NOT NULL,
  `nameGrouped` VARCHAR(200) DEFAULT ''        NOT NULL,
  `voltage`     DOUBLE DEFAULT '0'             NOT NULL,
  `desc`        VARCHAR(5000) DEFAULT ''       NOT NULL,
  `imgFileName` VARCHAR(200) DEFAULT ''        NOT NULL,
  `numShowed`   INT UNSIGNED DEFAULT '0'       NOT NULL,
  `categoryFK`  INT UNSIGNED DEFAULT '0'       NOT NULL,
  `ingredType`  VARCHAR(10) DEFAULT 'NON_ALCO' NOT NULL
)
  ENGINE = InnoDB;


INSERT INTO `ingredient` (`id`, `name`, `nameGrouped`, `voltage`, `desc`, `imgFileName`, `numShowed`, `categoryFK`, `ingredType`)
  SELECT
    `id`,
    `name`,
    `nameGrouped`,
    `voltage`,
    `desc`,
    `imgFileName`,
    `numShowed`,
    `categoryFK`,
    "ALCO"
  FROM `coctail_ingred_alco`;


INSERT INTO `ingredient` (`id`, `name`, `nameGrouped`, `desc`, `imgFileName`, `numShowed`, `categoryFK`, `voltage`, `ingredType`)
  SELECT
    `id`,
    `name`,
    `nameGrouped`,
    `desc`,
    `imgFileName`,
    `numShowed`,
    `categoryFK`,
    0,
    "NON_ALCO"
  FROM `coctail_ingred_alco_non`;


DROP TABLE IF EXISTS `coctail_ingredients`;

-- auto-generated definition
CREATE TABLE `coctail_ingredients`
(
  `coctail_id`    INT(10)     NOT NULL,
  `ingredient_id` INT(10)     NOT NULL,
  `volume`        VARCHAR(10) NULL
)
  ENGINE = InnoDB;

INSERT INTO `coctail_ingredients` (`coctail_id`, `ingredient_id`, `volume`)
  SELECT
    `coctail_id`,
    `ingred_non_alco_id`,
    `volume`
  FROM `coctail_non_alco_ingredients`;


INSERT INTO `coctail_ingredients` (`coctail_id`, `ingredient_id`, `volume`)
  SELECT
    `coctail_id`,
    `ingred_alco_id`,
    `volume`
  FROM `coctail_alco_ingredients`;

DROP TABLE IF EXISTS `coctail_ingred_nutririon`;
DROP TABLE IF EXISTS `coctail_ingred_alco`;
DROP TABLE IF EXISTS `coctail_ingred_alco_non`;
DROP TABLE IF EXISTS `coctail_non_alco_ingredients`;
DROP TABLE IF EXISTS `coctail_alco_ingredients`;