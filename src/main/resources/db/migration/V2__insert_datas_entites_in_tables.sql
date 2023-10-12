INSERT INTO tb_customers (name, email, address)
VALUES ('John Doe', 'john.doe@example.com', '123 Main St'),
       ('Jane Smith', 'janesmith@example.com', '456 Elm St, Town'),
       ('Alice Johnson', 'alice@example.com', '789 Oak Ave, Village'),
       ('Bob Williams', 'bob@example.com', '101 Pine St, Suburb'),
       ('Eve Davis', 'eve@example.com', '246 Cedar Rd, County'),
       ('Charlie Brown', 'charlie@example.com', '555 Maple Ln, Hamlet');

INSERT INTO tb_products (name, quantity, version)
VALUES ('Product A', 10, 1);
insert into tb_products (name, quantity, version)
values ('Juice - Tomato, 10 Oz', 240, 100);
insert into tb_products (name, quantity, version)
values ('Grapefruit - Pink', 160, 77);
insert into tb_products (name, quantity, version)
values ('Chicken - Wings, Tip Off', 245, 29);
insert into tb_products (name, quantity, version)
values ('Fenngreek Seed', 368, 70);
insert into tb_products (name, quantity, version)
values ('Carrots - Mini Red Organic', 437, 33);
insert into tb_products (name, quantity, version)
values ('Rice - Sushi', 431, 63);
insert into tb_products (name, quantity, version)
values ('Beef - Top Butt Aaa', 283, 71);
insert into tb_products (name, quantity, version)
values ('Foil - 4oz Custard Cup', 683, 76);
insert into tb_products (name, quantity, version)
values ('Brownies - Two Bite, Chocolate', 592, 9);


INSERT INTO tb_orders (customer_id, order_date)
VALUES (1, '2023-09-19'),
       (6, '2023-09-20'),
       (3, '2023-10-01'),
       (2, '2023-11-15'),
       (5, '2023-12-05'),
       (2, '2024-01-20'),
       (4, '2024-03-14');

INSERT INTO tb_order_items (order_id, product_id, quantity)
VALUES (1, 1, 2),
       (7, 2, 5),
       (2, 7, 8),
       (6, 6, 12),
       (5, 5, 6),
       (4, 1, 23),
       (3, 3, 45),
       (6, 4, 45);