insert
into product(id, version, name, productCode, productType)
values((select nextval('hibernate_sequence')), 0, 'Typewriter, Mk. I.', 'TW.I', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Central Computing Unit', 'CCU', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Liquid Crystal Display', 'LCD', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Key Based Input Device, Mk. I', 'KBID.I', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Portable Touch-Screen Device, prototype', 'PTSD', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Reserve Power Pack', 'RPP', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Portable Computing Unit (without Display)', 'PCU', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Long Range Wireless Signal Enhancer, prototype', 'LRWSE', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Network Adapter', 'NA', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'High Performance Computing Unit, Mk.II', 'HPCU', 'EQUIPMENT')
  , ((select nextval('hibernate_sequence')), 0, 'Standard Mouse, Mk. I', 'SM.I', 'EQUIPMENT');
