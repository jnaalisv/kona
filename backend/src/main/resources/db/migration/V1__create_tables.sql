create sequence hibernate_sequence start 1 increment 1;

create table address (
    id int8 not null,
    municipality varchar(255),
    postal_code varchar(255),
    street varchar(255),
    version int8 not null,
    primary key (id)
);

create table customer (
    id int8 not null,
    create_time timestamp,
    name varchar(255),
    version int8 not null,
    primary key (id)
);

create table customer_addresses (
    customer_id int8 not null REFERENCES customer ON DELETE CASCADE,
    country_code varchar(255),
    municipality varchar(255),
    postal_code varchar(255),
    street varchar(255)
);

create table product (
     id int8 not null,
     create_time TIMESTAMP NOT NULL DEFAULT localtimestamp,
     name TEXT,
     price_amount int4,
     price_currency int4,
     product_code varchar(255),
     product_type varchar(255),
     version int8 not null,
     primary key (id),
     UNIQUE (product_code)
);

create table purchase_order (
    id int8 not null,
    create_time timestamp NOT NULL DEFAULT localtimestamp,
    orderer_id int8 not null references customer ON DELETE RESTRICT,
    version int8 not null,
    primary key (id)
);

create table order_line (
    id int8 not null,
    amount numeric(19, 2),
    product_code varchar(255) NOT NULL REFERENCES product(product_code) ON DELETE RESTRICT,
    product_id int8 not null REFERENCES product ON DELETE RESTRICT,
    purchase_order_id int8 REFERENCES purchase_order ON DELETE CASCADE,
    primary key (id)
);

create table "user" (
    id int8 not null,
    password varchar(255),
    username varchar(255),
    version int8 not null,
    primary key (id)
);