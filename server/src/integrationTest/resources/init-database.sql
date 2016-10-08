ALTER SEQUENCE hibernate_sequence RESTART WITH 1;
DELETE FROM users;
DELETE FROM Product;
DELETE FROM Customer;
DELETE FROM OrderLine;
DELETE FROM DeliveryOrder;
DELETE FROM address;