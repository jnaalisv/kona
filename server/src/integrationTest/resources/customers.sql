insert
into customer(id, name, version)
values
  ((select nextval('hibernate_sequence')), 'Some Customer', 0),
  ((select nextval('hibernate_sequence')), 'Another Customer', 0);