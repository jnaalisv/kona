CREATE SEQUENCE hibernate_sequence;

CREATE TABLE Users (
  id int8 NOT NULL,
  password VARCHAR(255),
  username VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE Product (
  id int8 NOT NULL,
  name VARCHAR(255),
  productCode VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE Address (
    id int8 NOT NULL,
    municipality VARCHAR(255),
    postalCode VARCHAR(255),
    street VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE Customer (
    id int8 NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE DeliveryOrder (
    id int8 NOT NULL,
    payerID int8 NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE OrderLine (
    id int8 NOT NULL,
    amount NUMERIC(19, 2),
    productCode VARCHAR(255) NOT NULL,
    deliveryOrder_id int8,
    PRIMARY KEY (id)
);
