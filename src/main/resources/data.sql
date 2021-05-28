insert into areas (id, area) values (1,'Sistemas');
insert into areas (id, area) values (2,'Recusos humanos');
insert into areas (id, area) values (3,'Mesa de entrada');
insert into areas (id, area) values (4,'Gerencia');
insert into areas (id, area) values (5,'Pagos');

insert into tipos_liquidaciones (id, tipo) values (1,'Normal');
insert into tipos_liquidaciones (id, tipo) values (2,'Extrordinaria');
insert into tipos_liquidaciones (id, tipo) values (3,'Horas extras');
insert into tipos_liquidaciones (id, tipo) values (4,'Extra');

insert into tipos_conceptos (id, tipo) values (1,'Remunerativo');
insert into tipos_conceptos (id, tipo) values (2,'No remunerativo');
insert into tipos_conceptos (id, tipo) values (3,'Deduccion');

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

-- CONCEPTOS --
insert into conceptos (id, importe, descripcion, tipo_concepto_id) values (1, 3000, "Por no faltar al trabajo", 1);

insert into conceptos (id, importe, descripcion, tipo_concepto_id) values (2, 5000, "Por no faltar al trabajo en temporada alta", 1);

insert into conceptos (id, importe, descripcion, tipo_concepto_id) values (3, 1000, "Antiguedad en cargo alto", 2);

insert into conceptos (id, importe, descripcion, tipo_concepto_id) values (4, 500, "Antiguedad en cargo bajo", 2);

insert into conceptos (id, importe, descripcion, tipo_concepto_id) values (5, 5000, "Obra social OSDE", 1);

insert into conceptos (id, importe, descripcion, tipo_concepto_id) values (6, 3500, "Obra social PAMI", 1);

insert into conceptos (id, importe, descripcion, tipo_concepto_id) values (7, 2000, "Jubilacion", 3);

insert into conceptos (id, importe, descripcion, tipo_concepto_id) values (8, 1500, "Sepelio", 3);
-- CONCEPTOS --

-- LIQUIDACIONES --
--1--
insert into liquidaciones (id, user_id, tipo_liquidacion_id, area, periodo, seccion, fecha_ingreso,
numero_recibo, total_remuneracion_con_aportes, total_remuneracion_sin_aportes, total_cobrar,deducciones) 
values (1, 1, 1, "sistemas", CURRENT_TIMESTAMP, "", CURRENT_TIMESTAMP, "1233412", 18000, 18000, 14000, 4000);

insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad) values (1, 1, 1);

insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad) values (1, 3, 5);

insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad) values (1, 5, 1);

insert into liquidacion_concepto (liquidacion_id, concepto_id, cantidad) values (1, 7, 1);
--1--
-- LIQUIDACIONES --


