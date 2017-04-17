insert
into customer(id, name, version)
values
  ((select nextval('hibernate_sequence')), 'Acme Corporation', 0),
  ((select nextval('hibernate_sequence')), 'Beyond Earth Mining Inc.', 0),
  ((select nextval('hibernate_sequence')), 'Business Machines Ltd.', 0),
  ((select nextval('hibernate_sequence')), 'Centauri Enterprise', 0),
  ((select nextval('hibernate_sequence')), 'Edison Corporation', 0),
  ((select nextval('hibernate_sequence')), 'Galaxy Industries', 0),
  ((select nextval('hibernate_sequence')), 'Terrestrial Heavy Industries Ltd.', 0),
  ((select nextval('hibernate_sequence')), 'Venture Reseach Labs', 0),
  ((select nextval('hibernate_sequence')), 'XYZ Holdings Inc.', 0);