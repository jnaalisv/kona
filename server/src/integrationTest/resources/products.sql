insert
into product(id, version, name, productCode, productType)
values((select nextval('hibernate_sequence')), 0, 'Titanium Chainmail', 'TCm', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Titanium Coil', 'TCl', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Uranium', 'Ur', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Iron ore', 'Io', 'EQUIPMENT');
