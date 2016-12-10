insert
into product(id, version, name, productCode, productType)
values((select nextval('hibernate_sequence')), 0, 'Blue Mountain', 'B1', 'FOODSTUFF')
  , ((select nextval('hibernate_sequence')), 0, 'Bourbon', 'B2', 'FOODSTUFF')
  , ((select nextval('hibernate_sequence')), 0, 'Catuai', 'C1', 'FOODSTUFF')
  , ((select nextval('hibernate_sequence')), 0, 'Caturra', 'C2', 'FOODSTUFF')
  , ((select nextval('hibernate_sequence')), 0, 'Colombian', 'C3', 'FOODSTUFF')
  , ((select nextval('hibernate_sequence')), 0, 'Gesha', 'G1', 'FOODSTUFF')
  , ((select nextval('hibernate_sequence')), 0, 'Kona', 'K1', 'FOODSTUFF')
  , ((select nextval('hibernate_sequence')), 0, 'Java', 'J1', 'FOODSTUFF')
  , ((select nextval('hibernate_sequence')), 0, 'Sumatra Lintong', 'S1', 'FOODSTUFF')
  , ((select nextval('hibernate_sequence')), 0, 'Sumatra Mandheling', 'S2', 'FOODSTUFF');
