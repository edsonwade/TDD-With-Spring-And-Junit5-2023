create table products
(
    id       Integer       NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(128)  NOT NULL,
    quantity INTEGER       NOT NULL,
    version  INTEGER       NOT NULL,
    price    decimal(8, 2) NOT NULL,
    PRIMARY KEY (id)

);