insert
into product(id, version, name, productCode)
values((select nextval('hibernate_sequence')), 0, 'Blue Mountain', 'B1')
  , ((select nextval('hibernate_sequence')), 0, 'Bourbon', 'B2')
  , ((select nextval('hibernate_sequence')), 0, 'Catuai', 'C1')
  , ((select nextval('hibernate_sequence')), 0, 'Caturra', 'C2')
  , ((select nextval('hibernate_sequence')), 0, 'Colombian', 'C3')
  , ((select nextval('hibernate_sequence')), 0, 'Gesha', 'G1')
  , ((select nextval('hibernate_sequence')), 0, 'Kona', 'K1')
  , ((select nextval('hibernate_sequence')), 0, 'Java', 'J1')
  , ((select nextval('hibernate_sequence')), 0, 'Sumatra Lintong', 'S1')
  , ((select nextval('hibernate_sequence')), 0, 'Sumatra Mandheling', 'S2');
