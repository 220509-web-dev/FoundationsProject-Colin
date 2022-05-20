set search_path to foundation_project;

insert into users (first_name,last_name,username,"password",email) 
values ('Colin','Buckley','cbuckley','password','cbuckley@email.com'),
('Drew','Carey','dcarey','password','dcarey@price.com'),
('Bob','Barker','bbarker','password','bbacker@price.com'),
('Fluffy','Dragon','fdragon','password','fdragon@email.com'),
('Rainbow','Kitten','rkitten','password','rkitten@email.com');

insert into user_role_types (role_name)
values ('User'),
('Admin'),
('IT'),
('Help Desk'),
('Desktop');

insert into users_roles (user_id,role_id)
values (1,1),
(1,2),
(2,1),
(3,1),
(4,1),
(4,2),
(4,4),
(5,1),
(5,3),
(5,5)

select *
from users u 

select *
from users_roles ur 

select *
from user_role_types urt 

select urt.role_name, u.first_name, u.last_name 
from users u 
	join users_roles ur 
		on u.user_id = ur.user_id 
	join user_role_types urt 
		on ur.role_id = urt.role_id 

