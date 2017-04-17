INSERT
INTO
    purchaseorder(id, orderer_id, version)
VALUES
    ((select nextval('hibernate_sequence')), (select id from customer where name = 'Acme Corporation'), 0);

INSERT
INTO
    orderline(id, purchaseorder_id, productcode, amount, product_id)
VALUES
    ((select nextval('hibernate_sequence')), (select id from purchaseorder), 'TW.I', 10, (select id from product where productCode ='TW.I')),
    ((select nextval('hibernate_sequence')), (select id from purchaseorder), 'PTSD', 1, (select id from product where productCode ='PTSD'));
