

delete from user_role;
delete from roles;
delete from users;

insert into roles(role_id, name) values(1,'ROLE_USER');

-- -- pwd : 'admin@123'
insert into users(user_id, email, password, name) values(1,'siva@gmail.com','$2a$10$WMaaRjY2Q58nenATA/Vjh.VWmKY1En0TAWyuinLM.Fmx.c5hWOFRO','Siva Teja');
insert into users(user_id, email, password, name) values(2,'siva2@gmail.com','$2a$10$WMaaRjY2Q58nenATA/Vjh.VWmKY1En0TAWyuinLM.Fmx.c5hWOFRO','Sai');
insert into user_role (user_id, role_id) values(1,1);

INSERT INTO user_accounts (user_id, account_number, balance, account_type) VALUES (1, '11111111', 1000, 'SAVINGS');
INSERT INTO user_accounts (user_id, account_number, balance, account_type) VALUES (2, '22222222', 1000, 'SAVINGS');

INSERT INTO transactions(account_number, amount, date,  comments, status, type) VALUES (1,100, NOW(), 'Dummy Debit message', 'Success', 'CREDIT');
INSERT INTO transactions(account_number, amount, date,  comments, status, type) VALUES (1,100, NOW(), 'Dummy Debit message', 'Success', 'DEBIT');
INSERT INTO transactions(account_number, amount, date,  comments, status, type) VALUES (1,100, NOW(), 'Dummy Debit message', 'Success', 'CREDIT');
INSERT INTO transactions(account_number, amount, date,  comments, status, type) VALUES (1,100, NOW(), 'Dummy Debit message', 'Success', 'DEBIT');
INSERT INTO transactions(account_number, amount, date,  comments, status, type) VALUES (1,100, NOW(), 'Dummy Debit message', 'Success', 'CREDIT');
INSERT INTO transactions(account_number, amount, date,  comments, status, type) VALUES (1,100, NOW(), 'Dummy Debit message', 'Success', 'DEBIT');




