DROP TABLE `coctail_user_session`;
DROP TABLE `coctail_user_coctail_prepared`;
DROP TABLE `coctail_user_coctail_purchased`;


ALTER TABLE `coctail_user_picture`
  ADD COLUMN `userId` LONG NULL
  AFTER `id`;
ALTER TABLE `coctail_user_comment`
  ADD COLUMN `userId` LONG NULL
  AFTER `id`;
ALTER TABLE `coctail_user_favorite`
  ADD COLUMN `userId` LONG NULL
  AFTER `id`;
ALTER TABLE `coctail_user_feedback`
  ADD COLUMN `userId` LONG NULL
  AFTER `id`;
ALTER TABLE `coctail_user_follower`
  ADD COLUMN `userId` LONG NULL
  AFTER `id`;
ALTER TABLE `coctail_user_rating`
  ADD COLUMN `userId` LONG NULL
  AFTER `id`;
ALTER TABLE `coctail_user_violation`
  ADD COLUMN `userId` LONG NULL
  AFTER `id`;


UPDATE `coctail_user_picture` target INNER JOIN `coctail_user` userTable ON target.userFK = userTable.userID
SET target.`userId` = userTable.id;

UPDATE `coctail_user_comment` target INNER JOIN `coctail_user` userTable ON target.userFK = userTable.userID
SET target.`userId` = userTable.id;

UPDATE `coctail_user_favorite` target INNER JOIN `coctail_user` userTable ON target.userFK = userTable.userID
SET target.`userId` = userTable.id;

UPDATE `coctail_user_feedback` target INNER JOIN `coctail_user` userTable ON target.userFK = userTable.userID
SET target.`userId` = userTable.id;

UPDATE `coctail_user_follower` target INNER JOIN `coctail_user` userTable ON target.userFK = userTable.userID
SET target.`userId` = userTable.id;

UPDATE `coctail_user_rating` target INNER JOIN `coctail_user` userTable ON target.userFK = userTable.userID
SET target.`userId` = userTable.id;

UPDATE `coctail_user_violation` target INNER JOIN `coctail_user` userTable ON target.userFK = userTable.userID
SET target.`userId` = userTable.id;


ALTER TABLE `coctail_user_picture`
  DROP COLUMN `userFK`;
ALTER TABLE `coctail_user_comment`
  DROP COLUMN `userFK`;
ALTER TABLE `coctail_user_favorite`
  DROP COLUMN `userFK`;
ALTER TABLE `coctail_user_feedback`
  DROP COLUMN `userFK`;
ALTER TABLE `coctail_user_follower`
  DROP COLUMN `userFK`;
ALTER TABLE `coctail_user_rating`
  DROP COLUMN `userFK`;
ALTER TABLE `coctail_user_violation`
  DROP COLUMN `userFK`;