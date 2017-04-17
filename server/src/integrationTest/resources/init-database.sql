ALTER SEQUENCE hibernate_sequence RESTART WITH 1;
DELETE FROM Users;
DELETE FROM PurchaseOrder;
DELETE FROM OrderLine;
DELETE FROM Product;
DELETE FROM Customer;
DELETE FROM Address;

insert
into Users(id, version, username, password)
values((select nextval('hibernate_sequence')), 0, 'admin', '$2a$10$Im9A1gzTtB/1fXEGBEaNcOMZr3bMfGnAm0KZwuxIkSd98ElEYnQNm')
  , ((select nextval('hibernate_sequence')), 0, 'user', '$2a$10$ChpDbiy6ak8qXdJOd15POODaagSKuN7SzDJaUnCQlFWyM3aPraCoa');