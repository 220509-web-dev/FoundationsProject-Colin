create schema foundation_project;

set search_path to foundation_project;

create table users (
	user_id INT generated always as identity primary key,
	first_name VARCHAR(25) not null,
	last_name VARCHAR(30),
	username VARCHAR(25),
	password VARCHAR(40),
	email VARCHAR(50)
);

create table user_role_types (
	role_id INT generated always as identity primary key,
	role_name VARCHAR(25)
);

create table users_roles (
	user_id INT,
	role_id INT,
	
	constraint fk_users
		foreign key(user_id)
			references users(user_id),
	constraint fk_roles
		foreign key(role_id)
			references user_role_types(role_id)
);
