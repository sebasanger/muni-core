--USESRS--
-- Contraseña: Admin1
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled,cuil, cuit, numero_legajo) 
values (1, 'Sebastian Sangermano', 'seba.sanger88@gmail.com','seba.sanger88@gmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC'
,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true,"20416166081","2041616608",  "1008");

insert into user_entity_roles (user_entity_id, roles) values (1,'USER');
insert into user_entity_roles (user_entity_id, roles) values (1,'ADMIN');

-- Contraseña: admin
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled,cuil, cuit, numero_legajo) 
values (2, 'Hector Selleski', 'hselleski@gmail.com','hselleski@gmail.com','$2a$04$4X2.gX.iYZpqyJGliIDP.evdubunBFAOebikWxGcJp74QMeVX1UTC'
 ,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, "56465456","4564654", "554548");

insert into user_entity_roles (user_entity_id, roles) values (2,'ADMIN');
insert into user_entity_roles (user_entity_id, roles) values (2,'USER');

-- Contraseña: user
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled,cuil, cuit, numero_legajo) 
values (3, 'User', 'user@hotmail.com','user@hotmail.com','$2a$04$xwhhMoyJDNiOxhnnbATDO.YZAAElNcSIf.y7G8.cBhH8IHSlaOgsG',
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true, "12323425", "678678", "23423");

insert into user_entity_roles (user_entity_id, roles) values (3,'USER');

-- USESRS --
