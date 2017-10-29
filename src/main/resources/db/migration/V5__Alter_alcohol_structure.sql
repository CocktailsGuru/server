ALTER TABLE coctail_glass ADD `desc` varchar(500) DEFAULT NULL;

UPDATE coctail_glass g SET `desc` = (
  SELECT distinct glassDesc FROM coctail c WHERE g.id = c.glassFK LIMIT 1
);

ALTER TABLE coctail DROP COLUMN glassDesc;


ALTER TABLE coctail_method ADD `desc` varchar(500) DEFAULT NULL;

UPDATE coctail_method m SET `desc` = (
  SELECT distinct methodDesc FROM coctail c WHERE m.id = c.methodFK LIMIT 1
);

ALTER TABLE coctail DROP COLUMN methodDesc;


