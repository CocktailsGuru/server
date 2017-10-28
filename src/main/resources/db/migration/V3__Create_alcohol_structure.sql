/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


--
-- Table structure for table `coctail`
--

DROP TABLE IF EXISTS `coctail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL DEFAULT '',
  `glassFK` int(11) NOT NULL DEFAULT '-1',
  `glassDesc` varchar(500) DEFAULT NULL,
  `methodFK` int(11) NOT NULL DEFAULT '-1',
  `methodDesc` varchar(500) DEFAULT NULL,
  `tasteFK` int(11) NOT NULL DEFAULT '-1',
  `ingredientsIds` varchar(200) NOT NULL DEFAULT '[]',
  `ingredients` varchar(2000) DEFAULT NULL,
  `numIngredTotal` int(10) unsigned NOT NULL DEFAULT '0',
  `numIngredAlko` int(10) unsigned NOT NULL DEFAULT '0',
  `numIngredAlkoNon` int(10) unsigned NOT NULL DEFAULT '0',
  `volumeTotal` int(11) NOT NULL DEFAULT '0',
  `volumeAlko` int(11) NOT NULL DEFAULT '0',
  `volumeAlkoNon` int(11) NOT NULL DEFAULT '0',
  `instructions` varchar(2000) DEFAULT NULL,
  `garnish` varchar(2000) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `imgFileName` varchar(200) DEFAULT NULL,
  `numRating` int(10) unsigned NOT NULL DEFAULT '0',
  `numRating1` int(10) unsigned NOT NULL DEFAULT '0',
  `numRating2` int(10) unsigned NOT NULL DEFAULT '0',
  `numRating3` int(10) unsigned NOT NULL DEFAULT '0',
  `numRating4` int(10) unsigned NOT NULL DEFAULT '0',
  `numRating5` int(10) unsigned NOT NULL DEFAULT '0',
  `numLikes` int(10) unsigned NOT NULL DEFAULT '0',
  `numPictures` int(10) unsigned NOT NULL DEFAULT '0',
  `numComments` int(10) unsigned NOT NULL DEFAULT '0',
  `numPrepared` int(10) unsigned NOT NULL DEFAULT '0',
  `numPurchased` int(10) unsigned NOT NULL DEFAULT '0',
  `numShowed` int(10) unsigned NOT NULL DEFAULT '0',
  `alcoholVolume` double NOT NULL DEFAULT '0',
  `kcalVolume` int(10) unsigned NOT NULL DEFAULT '0',
  `flags` varchar(300) NOT NULL DEFAULT '[]' COMMENT 'digestive, etc etc',
  `videoUrl` varchar(300) NOT NULL DEFAULT '[]' COMMENT 'vie url',
  `toolsIds` varchar(300) NOT NULL DEFAULT '[]' COMMENT 'shaker etc etc',
  `flavorID` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'flavor ID',
  `flavor` varchar(100) NOT NULL DEFAULT '' COMMENT 'bitter etc',
  `groupType` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'iba cocotails etc',
  `groupType2` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'nejaka podgroupa dalsie',
  `groupType3` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'nejaka pod groupa',
  `dataCol` varchar(4000) NOT NULL DEFAULT '' COMMENT 'nejaky poistny stlpcek an hovadiny',
  `simillarDrinks` varchar(2000) NOT NULL DEFAULT '[]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17010 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `coctail_glass`
--

DROP TABLE IF EXISTS `coctail_glass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_glass` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_ingred_alco`
--

DROP TABLE IF EXISTS `coctail_ingred_alco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_ingred_alco` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '',
  `nameGrouped` varchar(200) NOT NULL DEFAULT '' COMMENT 'Voda : Orange Falv voda ',
  `nameModif` varchar(200) NOT NULL DEFAULT '' COMMENT 'Absolut Vodka / branded',
  `voltage` double NOT NULL DEFAULT '-1',
  `desc` varchar(5000) NOT NULL DEFAULT '',
  `imgFileName` varchar(200) NOT NULL DEFAULT '',
  `videoUrl` varchar(200) NOT NULL DEFAULT '',
  `websiteUrl` varchar(400) NOT NULL DEFAULT '',
  `tasteFK` int(10) unsigned NOT NULL DEFAULT '0',
  `numShowed` int(10) unsigned NOT NULL DEFAULT '0',
  `dataCol` varchar(4000) NOT NULL DEFAULT '' COMMENT 'column for some furure data',
  `categoryFK` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'nieco ako vodka, whiskey a podoben sracky',
  `numKcal` double NOT NULL DEFAULT '0' COMMENT 'naprikal 265kcal na 100 ml',
  `flags` varchar(200) NOT NULL DEFAULT '' COMMENT 'nejake znacky',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=227 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_ingred_alco_non`
--

DROP TABLE IF EXISTS `coctail_ingred_alco_non`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_ingred_alco_non` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '',
  `nameGrouped` varchar(200) NOT NULL DEFAULT '',
  `nameModif` varchar(200) NOT NULL DEFAULT '',
  `desc` varchar(5000) NOT NULL DEFAULT '',
  `preparation` varchar(5000) NOT NULL DEFAULT '',
  `imgFileName` varchar(200) NOT NULL DEFAULT '',
  `videoUrl` varchar(200) NOT NULL DEFAULT '',
  `websiteUrl` varchar(400) NOT NULL DEFAULT '',
  `tasteFK` int(10) unsigned NOT NULL DEFAULT '0',
  `numShowed` int(10) unsigned NOT NULL DEFAULT '0',
  `numKcal` double NOT NULL DEFAULT '0',
  `categoryFK` int(10) unsigned NOT NULL DEFAULT '0',
  `dataCol` varchar(4000) NOT NULL DEFAULT '',
  `flags` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1182 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_ingred_category`
--

DROP TABLE IF EXISTS `coctail_ingred_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_ingred_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '',
  `desc` varchar(5000) NOT NULL DEFAULT '',
  `preparation` varchar(5000) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 PACK_KEYS=1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_ingred_matrix`
--

DROP TABLE IF EXISTS `coctail_ingred_matrix`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_ingred_matrix` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `coctailFK` int(11) NOT NULL DEFAULT '-1',
  `ingredientFK` int(11) NOT NULL DEFAULT '-1',
  `glassFK` int(11) NOT NULL DEFAULT '-1',
  `methodFK` int(11) NOT NULL DEFAULT '-1',
  `tasteFK` int(11) NOT NULL DEFAULT '-1',
  `ingredientsCount` int(11) NOT NULL DEFAULT '-1',
  `ingredientsCountAlko` int(11) NOT NULL DEFAULT '-1',
  `ingredientsCountAlkoNon` int(11) NOT NULL DEFAULT '-1',
  `volumeTotal` int(11) NOT NULL DEFAULT '-1',
  `volumeAlko` int(11) NOT NULL DEFAULT '-1',
  `volumeAlkoNon` int(11) NOT NULL DEFAULT '-1',
  `alcoholVolume` double NOT NULL DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68112 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_ingred_nutririon`
--

DROP TABLE IF EXISTS `coctail_ingred_nutririon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_ingred_nutririon` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `val1` double NOT NULL DEFAULT '0',
  `val2` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_method`
--

DROP TABLE IF EXISTS `coctail_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_method` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET latin1 NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_object_type`
--

DROP TABLE IF EXISTS `coctail_object_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_object_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_package`
--

DROP TABLE IF EXISTS `coctail_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_package` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '',
  `description` varchar(5000) NOT NULL DEFAULT '',
  `imgFileName` varchar(100) NOT NULL DEFAULT '',
  `numShown` int(10) unsigned NOT NULL DEFAULT '0',
  `isNew` tinyint(1) NOT NULL DEFAULT '0',
  `cocktailFKs` varchar(4000) NOT NULL DEFAULT '',
  `position` int(10) unsigned NOT NULL DEFAULT '0',
  `isNewCocktails` int(11) DEFAULT '0',
  `price` varchar(50) NOT NULL DEFAULT '',
  `purchase_id` varchar(50) NOT NULL DEFAULT '',
  `ct` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;




--
-- Table structure for table `coctail_type`
--

DROP TABLE IF EXISTS `coctail_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '',
  `desc` varchar(2000) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_user`
--

DROP TABLE IF EXISTS `coctail_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userID` varchar(100) NOT NULL DEFAULT '',
  `userPushID` varchar(300) NOT NULL DEFAULT '',
  `userEmail` varchar(100) NOT NULL DEFAULT '',
  `userName` varchar(200) NOT NULL DEFAULT '',
  `userGender` int(10) unsigned NOT NULL DEFAULT '0',
  `userAge` int(10) unsigned NOT NULL DEFAULT '0',
  `userCountryCode` varchar(10) NOT NULL DEFAULT '',
  `lastDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastGps` varchar(150) NOT NULL DEFAULT '',
  `userImage` varchar(200) NOT NULL DEFAULT '',
  `numSessions` int(11) NOT NULL DEFAULT '0',
  `numPictures` int(11) NOT NULL DEFAULT '0',
  `numPicturesFav` int(11) NOT NULL DEFAULT '0',
  `numComments` int(11) NOT NULL DEFAULT '0',
  `numFollowers` int(11) NOT NULL DEFAULT '0',
  `numFollowing` int(11) NOT NULL DEFAULT '0',
  `numCocktailsFav` int(11) NOT NULL DEFAULT '0',
  `numCocktailsRat` int(11) NOT NULL DEFAULT '0',
  `numCocktailsPrep` int(11) NOT NULL DEFAULT '0',
  `numCocktailsPurch` int(11) NOT NULL DEFAULT '0',
  `numPoints` int(11) NOT NULL DEFAULT '0',
  `numShown` int(11) NOT NULL DEFAULT '0',
  `userFBAgeGroup` varchar(45) NOT NULL DEFAULT '',
  `userFBLanguage` varchar(45) NOT NULL DEFAULT '',
  `userFBCountry` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7172 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_user_coctail_prepared`
--

DROP TABLE IF EXISTS `coctail_user_coctail_prepared`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_user_coctail_prepared` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `coctailFK` int(11) NOT NULL DEFAULT '-1',
  `userFK` varchar(100) NOT NULL DEFAULT '',
  `remark` varchar(2000) NOT NULL DEFAULT '',
  `isRemarkPublic` tinyint(1) NOT NULL DEFAULT '0',
  `rating` int(11) NOT NULL DEFAULT '-1',
  `ct` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_user_coctail_purchased`
--

DROP TABLE IF EXISTS `coctail_user_coctail_purchased`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_user_coctail_purchased` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `coctail_user_comment`
--

DROP TABLE IF EXISTS `coctail_user_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_user_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `objectTypeFK` int(11) NOT NULL DEFAULT '-1',
  `objectFK` int(11) NOT NULL DEFAULT '-1',
  `objectName` varchar(200) NOT NULL DEFAULT '',
  `userFK` varchar(100) NOT NULL DEFAULT '',
  `content` varchar(4000) NOT NULL DEFAULT '',
  `numLikes` int(11) NOT NULL DEFAULT '0',
  `numLikesDis` int(11) NOT NULL DEFAULT '0',
  `isVisible` tinyint(1) NOT NULL DEFAULT '1',
  `hiddenReason` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'pokial ej  isVisible false tak bol bud deletnuty, aelbo porusil pravidla',
  `ut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ct` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_user_favorite`
--

DROP TABLE IF EXISTS `coctail_user_favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_user_favorite` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `objectTypeFK` int(11) NOT NULL DEFAULT '-1',
  `objectFK` int(11) NOT NULL DEFAULT '-1',
  `userFK` varchar(100) NOT NULL DEFAULT '',
  `ct` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8329 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `coctail_user_feedback`
--

DROP TABLE IF EXISTS `coctail_user_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_user_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userFK` varchar(100) NOT NULL DEFAULT '',
  `userEmail` varchar(100) NOT NULL DEFAULT '',
  `msgTitle` varchar(200) NOT NULL DEFAULT '',
  `msgBody` varchar(5000) NOT NULL DEFAULT '',
  `ct` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_user_follower`
--

DROP TABLE IF EXISTS `coctail_user_follower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_user_follower` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userFK` varchar(100) NOT NULL DEFAULT '',
  `followingFK` varchar(100) NOT NULL DEFAULT '',
  `ct` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `coctail_user_picture`
--

DROP TABLE IF EXISTS `coctail_user_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_user_picture` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `objectTypeFK` int(11) NOT NULL DEFAULT '-1',
  `objectFK` int(11) NOT NULL DEFAULT '-1',
  `objectName` varchar(200) NOT NULL DEFAULT '',
  `userFK` varchar(100) NOT NULL DEFAULT '',
  `description` varchar(4000) NOT NULL DEFAULT '',
  `fileName` varchar(200) NOT NULL DEFAULT '',
  `numLikes` int(11) NOT NULL DEFAULT '0',
  `numLikesDis` int(11) NOT NULL DEFAULT '0',
  `numFav` int(11) NOT NULL DEFAULT '0',
  `numComments` int(11) NOT NULL DEFAULT '0',
  `numShowed` int(10) unsigned NOT NULL DEFAULT '0',
  `isVisible` tinyint(1) NOT NULL DEFAULT '1',
  `hiddenReason` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'pokial ej  isVisible false tak bol bud deletnuty, aelbo porusil pravidla',
  `ut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ct` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=648 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_user_push_android`
--

DROP TABLE IF EXISTS `coctail_user_push_android`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_user_push_android` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userFK` varchar(100) DEFAULT NULL,
  `token` varchar(300) DEFAULT NULL,
  `ut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ct` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_user_rating`
--

DROP TABLE IF EXISTS `coctail_user_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_user_rating` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `objectTypeFK` int(11) NOT NULL DEFAULT '-1',
  `objectFK` int(11) NOT NULL DEFAULT '-1',
  `userFK` varchar(100) NOT NULL DEFAULT '',
  `rating` int(11) NOT NULL DEFAULT '-1',
  `ct` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=980 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_user_session`
--

DROP TABLE IF EXISTS `coctail_user_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_user_session` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userFK` varchar(100) NOT NULL DEFAULT '',
  `gps` varchar(100) NOT NULL DEFAULT '',
  `country` varchar(100) NOT NULL DEFAULT '',
  `province` varchar(100) NOT NULL DEFAULT '',
  `city` varchar(100) NOT NULL DEFAULT '',
  `postalCode` varchar(10) NOT NULL DEFAULT '',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_user_violation`
--

DROP TABLE IF EXISTS `coctail_user_violation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_user_violation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `objectTypeFK` int(10) unsigned NOT NULL DEFAULT '0',
  `objectFK` int(10) unsigned NOT NULL DEFAULT '0',
  `objectUserFK` varchar(100) NOT NULL DEFAULT '' COMMENT 'ak ej reportnuty object typy user, tak ejho id je string a nie long ',
  `userFK` varchar(100) NOT NULL DEFAULT '',
  `reason` varchar(100) NOT NULL DEFAULT '',
  `resolved` tinyint(1) NOT NULL DEFAULT '0',
  `ct` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `coctail_x_config`
--

DROP TABLE IF EXISTS `coctail_x_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coctail_x_config` (
  `keyy` varchar(40) DEFAULT NULL,
  `valuee` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;