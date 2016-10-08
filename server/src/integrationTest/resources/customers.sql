insert
into customer(id, name)
values
  ((select nextval('hibernate_sequence')), 'Some Customer'),
  ((select nextval('hibernate_sequence')), 'Another Customer');