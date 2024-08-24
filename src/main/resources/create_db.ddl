-- データベース・データベースユーザの初期化
DROP DATABASE IF EXISTS db_rental;
DROP ROLE IF EXISTS usr_rental;
-- データベースユーザ・データベースの生成
CREATE ROLE usr_rental WITH PASSWORD 'himitu' LOGIN;
CREATE DATABASE db_rental OWNER usr_rental ENCODING 'utf8';