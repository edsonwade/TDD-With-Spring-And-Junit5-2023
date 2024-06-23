CREATE TABLE IF NOT EXISTS tb_customers
(
    customer_id SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    email       VARCHAR(255),
    address     VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tb_products
(
    product_id SERIAL PRIMARY KEY,
    name       VARCHAR(255),
    quantity   INT,
    version    INT
);

CREATE TABLE IF NOT EXISTS tb_orders
(
    order_id    SERIAL PRIMARY KEY,
    customer_id INT,
    order_date  TIMESTAMP,
    FOREIGN KEY (customer_id)
        REFERENCES tb_customers (customer_id)
        ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS tb_order_items
(
    order_item_id SERIAL PRIMARY KEY,
    order_id      INT,
    product_id    INT,
    quantity      INT,
    FOREIGN KEY (order_id) REFERENCES tb_orders (order_id),
    FOREIGN KEY (product_id) REFERENCES tb_products (product_id)
);

ALTER TABLE tb_customers
    ADD CONSTRAINT uc_tb_customer_email UNIQUE (email);
