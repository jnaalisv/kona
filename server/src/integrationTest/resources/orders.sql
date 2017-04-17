INSERT
INTO
    purchaseorder(id, orderer_id, version)
VALUES
    ((select nextval('hibernate_sequence')), (select id from customer where name = 'Acme Corporation'), 0);

INSERT
INTO
    orderline(id, purchaseorder_id, productcode, amount)
VALUES
    ((select nextval('hibernate_sequence')), (select id from purchaseorder), 'LCD', 10),
    ((select nextval('hibernate_sequence')), (select id from purchaseorder), 'CCU', 1);
