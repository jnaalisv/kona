insert
into product(id, name, productCode)
values((select nextval('hibernate_sequence')), 'Blue Mountain', 'B1')
  , ((select nextval('hibernate_sequence')), 'Bourbon', 'B2')
  , ((select nextval('hibernate_sequence')), 'Catuai', 'C1')
  , ((select nextval('hibernate_sequence')), 'Caturra', 'C2')
  , ((select nextval('hibernate_sequence')), 'Colombian', 'C3')
  , ((select nextval('hibernate_sequence')), 'Gesha', 'G1')
  , ((select nextval('hibernate_sequence')), 'Kona', 'K1')
  , ((select nextval('hibernate_sequence')), 'Java', 'J1')
  , ((select nextval('hibernate_sequence')), 'Sumatra Lintong', 'S1')
  , ((select nextval('hibernate_sequence')), 'Sumatra Mandheling', 'S2');