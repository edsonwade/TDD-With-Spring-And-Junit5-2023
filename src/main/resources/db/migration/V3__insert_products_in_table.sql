insert into tb_products (product_id, name, quantity, version) values (1, 'Plate - Foam, Bread And Butter', 284, 39);
insert into tb_products (product_id, name, quantity, version) values (2, 'Juice - Tomato, 10 Oz', 240, 100);
insert into tb_products (product_id, name, quantity, version) values (3, 'Grapefruit - Pink', 160, 77);
insert into tb_products (product_id, name, quantity, version) values (4, 'Chicken - Wings, Tip Off', 245, 29);
insert into tb_products (product_id, name, quantity, version) values (5, 'Fenngreek Seed', 368, 70);
insert into tb_products (product_id, name, quantity, version) values (6, 'Carrots - Mini Red Organic', 437, 33);
insert into tb_products (product_id, name, quantity, version) values (7, 'Rice - Sushi', 431, 63);
insert into tb_products (product_id, name, quantity, version) values (8, 'Beef - Top Butt Aaa', 283, 71);
insert into tb_products (product_id, name, quantity, version) values (9, 'Foil - 4oz Custard Cup', 683, 76);
insert into tb_products (product_id, name, quantity, version) values (10, 'Brownies - Two Bite, Chocolate', 592, 9);

INSERT INTO tb_customers (customer_id, name, email, address)
VALUES
    (1,'John Doe', 'johndoe@example.com', '123 Main St, City'),
    (2,'Jane Smith', 'janesmith@example.com', '456 Elm St, Town');

-- Insert data into the Order table
INSERT INTO tb_order (order_id ,customer_customer_id, local_date_time)
VALUES
    (1,1, '2023-09-19'),
    (2,2, '2023-09-20');

-- Insert data into the OrderItem table
INSERT INTO tb_order_items (order_item_id ,order_order_id, product_product_id, quantity)
VALUES
    (1, 1, 1, 10),
    (2, 1, 2, 5),
    (3, 2, 3, 8);