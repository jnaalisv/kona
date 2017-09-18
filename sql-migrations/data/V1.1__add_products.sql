insert
into product(id, version, name, productCode, productType, createdBy)
values((select nextval('hibernate_sequence')), 0, 'Typewriter, Mk. I.', 'TW.I', 'EQUIPMENT', 'migration')
  , ((select nextval('hibernate_sequence')), 0, 'Central Computing Unit', 'CCU', 'EQUIPMENT', 'migration')
  , ((select nextval('hibernate_sequence')), 0, 'Liquid Crystal Display', 'LCD', 'EQUIPMENT', 'migration')
  , ((select nextval('hibernate_sequence')), 0, 'Key Based Input Device, Mk. I', 'KBID.I', 'EQUIPMENT', 'migration')
  , ((select nextval('hibernate_sequence')), 0, 'Portable Touch-Screen Device, prototype', 'PTSD', 'EQUIPMENT', 'migration')
  , ((select nextval('hibernate_sequence')), 0, 'Reserve Power Pack', 'RPP', 'EQUIPMENT', 'migration')
  , ((select nextval('hibernate_sequence')), 0, 'Portable Computing Unit (without Display)', 'PCU', 'EQUIPMENT', 'migration')
  , ((select nextval('hibernate_sequence')), 0, 'Long Range Wireless Signal Enhancer, prototype', 'LRWSE', 'EQUIPMENT', 'migration')
  , ((select nextval('hibernate_sequence')), 0, 'Network Adapter', 'NA', 'EQUIPMENT', 'migration')
  , ((select nextval('hibernate_sequence')), 0, 'High Performance Computing Unit, Mk.II', 'HPCU', 'EQUIPMENT', 'migration')
  , ((select nextval('hibernate_sequence')), 0, 'Standard Mouse, Mk. I', 'SM.I', 'EQUIPMENT', 'migration');
