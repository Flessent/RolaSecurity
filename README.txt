
Before you install the app, you firstly install the Database(Postgres 12 is using in this case) in order to execute all methods even logging.
You find below the table and some test values which you can use in order to go quickly because those Test value
 have been used for test wiht JUNIT or MOckito and also for Spring Security.
 However you can create your own ADMIN-User and other.
 The operation CRUD, Basic-Authentication , Test with JUNIt and Mockito work well.



CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS pgcrypto;
update users set password = crypt('1234567890', gen_salt('bf', 8)  ) 
create table produkt(
	id_produkt integer default nextval('seq_produkt') not null,
	name varchar(35) not null,
	price decimal (35) not null,
	quantity integer not null,
 verfuebarkeit boolean default false not null,
	saveDate DATE default CURRENT_DATE NOT null,
	CONSTRAINT UnicityNameProdukt UNIQUE(name)
);



create table  if not exists   users(username varchar(35) not null , password varchar(255) not null,
								   primary key (username));

CREATE TABLE IF NOT EXISTS  roles(
	code_role UUID default uuid_generate_v1(),
role varchar(100) not null,
description text,

CONSTRAINT UniciteRole UNIQUE(role),
CONSTRAINT PK_Role PRIMARY KEY (role),
CONSTRAINT UnicityCodeRole UNIQUE(code_role)

);


CREATE TABLE IF NOT EXISTS  users_roles(
	username varchar(100) not null,
code_role UUID not null,

CONSTRAINT FK_users FOREIGN KEY(username) REFERENCES users(username) on delete cascade on update cascade,
CONSTRAINT FK_role FOREIGN KEY(code_role) REFERENCES roles(code_role) on delete cascade on update cascade,
CONSTRAINT PK_roleUsers PRIMARY KEY(username,code_role),
CONSTRAINT UnicityRoleUsersUsername UNIQUE(username,code_role)

);

# Test values
insert into users values('flessent@gov.de', '1234567890')
update users set password = crypt('1234567890', gen_salt('bf', 8)  ) 

insert into roles(role) values('ADMIN')
insert into users_roles values('flessent@gov.de', '1edbca0e-f6b6-11ec-a574-744ca1ba4869')









