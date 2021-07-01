insert into areas (id, area, codigo) values (1,'Sistemas', "kjkszpj");
insert into areas (id, area, codigo) values (2,'Recusos humanos', "sdfsdf");
insert into areas (id, area, codigo) values (3,'Mesa de entrada', "ffad");
insert into areas (id, area, codigo) values (4,'Gerencia', "dfgnbcv");
insert into areas (id, area, codigo) values (5,'Pagos', "ertfgdfg");

--USESRS--
-- Contraseña: Admin1
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled,cuil, cuit, sueldo_basico, area_id, numero_legajo) 
values (1, 'Sebastian Sangermano', 'seba.sanger88@gmail.com','seba.sanger88@gmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC'
,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true,"20416166081","2041616608", 18000, 1, "65465");

insert into user_entity_roles (user_entity_id, roles) values (1,'USER');
insert into user_entity_roles (user_entity_id, roles) values (1,'ADMIN');

-- Contraseña: admin
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled,cuil, cuit, sueldo_basico, area_id, numero_legajo) 
values (2, 'Hector Selleski', 'hselleski@gmail.com','hselleski@gmail.com','$2a$04$4X2.gX.iYZpqyJGliIDP.evdubunBFAOebikWxGcJp74QMeVX1UTC'
 ,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, "56465456","4564654", 40000, 1, "554548");

insert into user_entity_roles (user_entity_id, roles) values (2,'ADMIN');
insert into user_entity_roles (user_entity_id, roles) values (2,'USER');

-- Contraseña: user
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled,cuil, cuit, sueldo_basico, area_id, numero_legajo) 
values (3, 'User', 'user@hotmail.com','user@hotmail.com','$2a$04$xwhhMoyJDNiOxhnnbATDO.YZAAElNcSIf.y7G8.cBhH8IHSlaOgsG',
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true, "12323425", "678678", 50000, 2,"23423");

insert into user_entity_roles (user_entity_id, roles) values (3,'USER');

-- Contraseña: admin
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled,cuil, cuit, sueldo_basico, area_id, numero_legajo) 
values (4, 'Admin', 'admin@gmail.com','admin@gmail.com','$2a$04$4X2.gX.iYZpqyJGliIDP.evdubunBFAOebikWxGcJp74QMeVX1UTC'
,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true, "456758", "45658", 20000, 5,"4564");

insert into user_entity_roles (user_entity_id, roles) values (4,'ADMIN');
insert into user_entity_roles (user_entity_id, roles) values (4,'USER');

-- Contraseña: user
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled,cuil, cuit, sueldo_basico, area_id, numero_legajo) 
values (5, 'User', 'user@gmail.com','user@gmail.com','$2a$04$xwhhMoyJDNiOxhnnbATDO.YZAAElNcSIf.y7G8.cBhH8IHSlaOgsG'
,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true, "456456467852", "2342342563", 15000, 4,"6788678");

insert into user_entity_roles (user_entity_id, roles) values (5,'USER');

-- Contraseña: disabled
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled,cuil, cuit, sueldo_basico, area_id, numero_legajo) 
values (6, 'Disabled', 'disabled@hotmail.com','disabled@hotmail.com','$2a$04$BaWG9mf4n7RLl6NXkPSBNeDAgpeFMjcptCI3Xdxzo4/mLkurpUxeK',
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,false,"345345354", "5475686896", 60000, 3,"345765");

insert into user_entity_roles (user_entity_id, roles) values (6,'USER');
-- USESRS --

-- -- CONCEPTOS --
-- insert into conceptos (id, descripcion, tipo_concepto) values (1, "Por no faltar al trabajo", "NO_REMUNERATIVO");

-- insert into conceptos (id, descripcion, tipo_concepto) values (2, "Por no faltar al trabajo en temporada alta", "NO_REMUNERATIVO");

-- insert into conceptos (id, descripcion, tipo_concepto) values (3, "Antiguedad en cargo alto", "REMUNERATIVO");

-- insert into conceptos (id, descripcion, tipo_concepto) values (4,"Antiguedad en cargo bajo", "NO_REMUNERATIVO");

-- insert into conceptos (id, descripcion, tipo_concepto) values (5, "Obra social OSDE", "REMUNERATIVO");

-- insert into conceptos (id, descripcion, tipo_concepto) values (6, "Obra social PAMI", "DEDUCCION");

-- insert into conceptos (id, descripcion, tipo_concepto) values (7, "Jubilacion", "REMUNERATIVO");

-- insert into conceptos (id, descripcion, tipo_concepto) values (8, "Sepelio", "DEDUCCION");
-- -- CONCEPTOS --

-- -- LIQUIDACIONES --
-- --1--
-- insert into liquidaciones (id, user_id, tipo_liquidacion_id, area, periodo, seccion, fecha_ingreso,
-- numero_recibo) 
-- values (1, 1, 1, "sistemas", CURRENT_TIMESTAMP, "", CURRENT_TIMESTAMP, "1233412");

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (1, 1, 1, 500);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (1, 3, 5, 600);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (1, 5, 1, 800);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (1, 7, 1, 1200);
-- --1--

-- --2--
-- insert into liquidaciones (id, user_id, tipo_liquidacion_id, area, periodo, seccion, fecha_ingreso,
-- numero_recibo) 
-- values (2, 2, 1, "Recusos humanos", CURRENT_TIMESTAMP, "asda", CURRENT_TIMESTAMP, "122333412");

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (2, 1, 1, 700);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (2, 5, 2, 400);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (2, 6, 1, 800);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (2, 2, 1, 850);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (2, 8, 1, 920);
-- --2--

-- --3--
-- insert into liquidaciones (id, user_id, tipo_liquidacion_id, area, periodo, seccion, fecha_ingreso,
-- numero_recibo) 
-- values (3, 3, 1, "Recusos humanos", CURRENT_TIMESTAMP, "afgsdgdg", CURRENT_TIMESTAMP, "23433234");

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (3, 2, 2, 700);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (3, 3, 1, 400);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (3, 6, 1, 1000);
-- --3--

-- --4--
-- insert into liquidaciones (id, user_id, tipo_liquidacion_id, area, periodo, seccion, fecha_ingreso,
-- numero_recibo) 
-- values (4, 3, 1, "Contabilidad", CURRENT_TIMESTAMP, "asda", CURRENT_TIMESTAMP, "122333412");

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (4, 1, 2, 700);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (4, 3, 3, 400);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (4, 6, 1, 800);
-- --4--

-- --5--
-- insert into liquidaciones (id, user_id, tipo_liquidacion_id, area, periodo, seccion, fecha_ingreso,
-- numero_recibo) 
-- values (5, 4, 1, "Gestion", CURRENT_TIMESTAMP, "asda", CURRENT_TIMESTAMP, "122333412");

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (5, 2, 1, 700);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (5, 5, 1, 400);

-- insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad, importe) values (5, 3, 2, 800);
-- --5--
-- -- LIQUIDACIONES --


