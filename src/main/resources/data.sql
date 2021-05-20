--USESRS--
-- Contraseña: Admin1
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (1, 'Sebastian Sangermano', 'seba.sanger88@gmail.com','seba.sanger88@gmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (1,'USER');
insert into user_entity_roles (user_entity_id, roles) values (1,'ADMIN');

-- Contraseña: admin
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (2, 'Hector Selleski', 'hselleski@gmail.com','hselleski@gmail.com','$2a$04$4X2.gX.iYZpqyJGliIDP.evdubunBFAOebikWxGcJp74QMeVX1UTC' ,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true);

insert into user_entity_roles (user_entity_id, roles) values (2,'ADMIN');
insert into user_entity_roles (user_entity_id, roles) values (2,'USER');

-- Contraseña: user
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (3, 'User', 'user@hotmail.com','user@hotmail.com','$2a$04$xwhhMoyJDNiOxhnnbATDO.YZAAElNcSIf.y7G8.cBhH8IHSlaOgsG',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (3,'USER');

-- Contraseña: admin
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (4, 'Admin', 'admin@gmail.com','admin@gmail.com','$2a$04$4X2.gX.iYZpqyJGliIDP.evdubunBFAOebikWxGcJp74QMeVX1UTC',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (4,'ADMIN');
insert into user_entity_roles (user_entity_id, roles) values (4,'USER');

-- Contraseña: user
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (5, 'User', 'user@gmail.com','user@gmail.com','$2a$04$xwhhMoyJDNiOxhnnbATDO.YZAAElNcSIf.y7G8.cBhH8IHSlaOgsG',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (5,'USER');

-- Contraseña: disabled
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (6, 'Disabled', 'disabled@hotmail.com','disabled@hotmail.com','$2a$04$BaWG9mf4n7RLl6NXkPSBNeDAgpeFMjcptCI3Xdxzo4/mLkurpUxeK',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,false);

insert into user_entity_roles (user_entity_id, roles) values (6,'USER');

-- Contraseña: Angelmartinez1
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (7, 'Gabriel Perez', 'gper@hotmail.com','gabper','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (7,'USER');
insert into user_entity_roles (user_entity_id, roles) values (7,'ADMIN');

-- Contraseña: Angelmartinez1
insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (9, 'Carlos Lopez', 'carlopez@hotmail.com','clop','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (9,'USER');

insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (10, 'Gabriel Sangermano', 'seba@hotmail.com','seba@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (10,'USER');

insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (11, 'Ricardo Marin', 'riki@hotmail.com','riki@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (11,'USER');

insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (12, 'Oscar Lopez', 'oscar@hotmail.com','oscar@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (12,'USER');

insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (13, 'Pedro Rodriguez', 'pedro@hotmail.com','pedro@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (13,'USER');

insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (14, 'Alberto Caseres', 'albert@hotmail.com','albert@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (14,'USER');

insert into users (id, full_name, email, username, password, created_at, last_password_change_at,enabled) 
values (15, 'Manuel kito', 'manukit@hotmail.com','manukit@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (15,'USER');
-- USESRS --






