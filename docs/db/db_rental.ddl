DROP TABLE IF EXISTS loans;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

/**********************************/
/* テーブル名: 利用者マスタ */
/**********************************/
CREATE TABLE users(
		id INTEGER NOT NULL,
		name VARCHAR(100) NOT NULL,
		phone VARCHAR(15),
		email VARCHAR(255)
);

/**********************************/
/* テーブル名: 製品マスタ */
/**********************************/
CREATE TABLE products(
		id SERIAL,
		imei CHARACTER(15) NOT NULL,
		serial_no INTEGER NOT NULL
);

/**********************************/
/* テーブル名: 貸出管理台帳 */
/**********************************/
CREATE TABLE loans(
		id SERIAL,
		product_serial INTEGER NOT NULL,
		user_id INTEGER NOT NULL,
		lend_date DATE NOT NULL,
		return_date DATE
);


ALTER TABLE users ADD CONSTRAINT IDX_users_PK PRIMARY KEY (id);
ALTER TABLE users ADD CONSTRAINT IDX_users UNIQUE (phone, email);

ALTER TABLE products ADD CONSTRAINT IDX_products_PK PRIMARY KEY (id);
ALTER TABLE products ADD CONSTRAINT IDX_products_imei UNIQUE (imei);
ALTER TABLE products ADD CONSTRAINT IDX_products_serial UNIQUE (serial_no);

ALTER TABLE loans ADD CONSTRAINT IDX_loan_PK PRIMARY KEY (id);
ALTER TABLE loans ADD CONSTRAINT IDX_loan_FK_user FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE loans ADD CONSTRAINT IDX_loan_FK_product FOREIGN KEY (product_serial) REFERENCES products (serial_no);

