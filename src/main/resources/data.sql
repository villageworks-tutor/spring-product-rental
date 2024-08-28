-- Insert initial users
INSERT INTO users (name, phone, email) VALUES ('user1', '090-1111-1111', 'user1@example.com');
INSERT INTO users (name, phone, email) VALUES ('user2', '03-0000-0000', 'user2@hoge.org');
INSERT INTO users (name, phone, email) VALUES ('user3', '06-9999-9999', 'user3@free.mail');

-- Insert initial products
INSERT INTO products (imei, serial_no) VALUES ('000000000000001', 1);
INSERT INTO products (imei, serial_no) VALUES ('000000000000002', 2);
INSERT INTO products (imei, serial_no) VALUES ('000000000000003', 3);

-- Insert initial rental records (assuming some products have already been rented)
INSERT INTO rentals (product_serial, user_id) VALUES (1, 1);
INSERT INTO rentals (product_serial, user_id, lend_date) VALUES (2, 2, '2024-08-20');
INSERT INTO rentals (product_serial, user_id, lend_date, return_date) VALUES (3, 3, '2024-08-21', '2024-08-22');
