ALTER TABLE coctail_user_rating
  MODIFY objectFK INT(10) UNSIGNED NOT NULL;
ALTER TABLE coctail_user_rating
  MODIFY userId INT(10) UNSIGNED NOT NULL;


ALTER TABLE coctail_user_favorite
  MODIFY objectFK INT(10) UNSIGNED NOT NULL;
ALTER TABLE coctail_user_favorite
  MODIFY userId INT(10) UNSIGNED NOT NULL;