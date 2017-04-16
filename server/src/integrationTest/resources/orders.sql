INSERT
INTO
    deliveryorder(id, ordererid, version)
VALUES
    ((select nextval('hibernate_sequence')), (select id from customer where name = 'Some Customer'), 0);

INSERT
INTO
    orderline(id, deliveryorder_id, productcode)
VALUES
    ((select nextval('hibernate_sequence')), (select id from deliveryorder), 'B1'),
    ((select nextval('hibernate_sequence')), (select id from deliveryorder), 'B2');
