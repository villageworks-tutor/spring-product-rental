-- Insert initial users
INSERT INTO users (username, email) VALUES
('user1', 'user1@example.com'),
('user2', 'user2@example.com'),
('user3', 'user3@example.com');

-- Insert initial products
INSERT INTO products (product_no, name, description, available) VALUES
('F001', '製品１', '製品１の説明', TRUE),
('F002', '製品２', '製品２の説明', TRUE),
('F003', '製品３', '製品３の説明', TRUE),
('F004', '製品４', '製品４の説明', TRUE);
-- ('Projector', 'Full HD projector for presentations', TRUE),
-- ('Camera', 'DSLR camera with 24MP sensor', TRUE),
-- ('Tablet', '10-inch tablet with a detachable keyboard', TRUE),
-- ('Smartphone', 'Latest model with 5G support', TRUE);

-- Insert initial rental records (assuming some products have already been rented)
-- INSERT INTO rental_records (product_id, user_id, rental_date) VALUES
-- (1, 1, '2024-08-01'),  -- user1 rented the Laptop on 2024-08-01
-- (2, 2, '2024-08-02');  -- user2 rented the Projector on 2024-08-02
