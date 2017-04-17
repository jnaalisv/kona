insert
into product(id, version, name, productCode, productType)
values((select nextval('hibernate_sequence')), 0, 'Typewriter, Mk. I.', 'TW.I', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Central Computing Unit', 'CCU', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Liquid Crystal Display', 'LCD', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Key Based Input Device, Mk. I', 'KBID.I', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Portable Touch-Screen Device, Prototype', 'PTSD', 'EQUIPMENT');
