CREATE SEQUENCE hibernate_sequence;

CREATE TABLE Users (
  id INT8 NOT NULL,
  version INT8 NOT NULL,
  password VARCHAR(255),
  username VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE Product (
  id INT8 NOT NULL,
  version INT8 NOT NULL,
  createTime TIMESTAMP NOT NULL DEFAULT localtimestamp,
  name TEXT,
  productCode VARCHAR(255),
  productType VARCHAR(255),
  priceAmount int4 DEFAULT 0,
  priceCurrency int4 DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE (productCode)
);

CREATE TABLE Customer (
  id INT8 NOT NULL,
  name VARCHAR(255),
  version INT8 NOT NULL,
  createTime TIMESTAMP NOT NULL DEFAULT localtimestamp,
  PRIMARY KEY (id)
);

create table Customer_addresses (
  customer_id int8 not null,
  countryCode varchar(255),
  municipality varchar(255),
  postalCode varchar(255),
  street varchar(255)
);

alter table Customer_addresses
  add constraint FK_CUSTOMER_ADDRESSES
  foreign key (customer_id)
  references Customer;

CREATE TABLE Address (
    id INT8 NOT NULL,
    municipality VARCHAR(255),
    postalCode VARCHAR(255),
    street VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE DeliveryOrder (
    id INT8 NOT NULL,
    payerID INT8 NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE OrderLine (
    id INT8 NOT NULL,
    amount NUMERIC(19, 2),
    productCode VARCHAR(255) NOT NULL,
    deliveryOrder_id INT8,
    PRIMARY KEY (id)
);
