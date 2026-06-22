insert into roles (name) values ('ADMIN');
insert into roles (name) values ('MANAGER');
insert into roles (name) values ('USER');

insert into users (username, email, password, role, telephone, status) values ('john_admin', 'john.admin@example.com', 'admin123', 'ADMIN', '9876543210', true);
insert into users (username, email, password, role, telephone, status) values ('sara_manager', 'sara.manager@example.com', 'manager123', 'MANAGER', '9876543211', true);
insert into users (username, email, password, role, telephone, status) values ('mike_user', 'mike.user@example.com', 'user123', 'USER', '9876543212', true);
insert into users (username, email, password, role, telephone, status) values ('alice_user', 'alice.user@manager.com',

insert into groups (name, description) values ('Admin Group', 'Group for admin users');
insert into groups (name, description) values ('Manager Group', 'Group for manager users');
insert into groups (name, description) values ('User Group', 'Group for regular users');

insert into role_users (role_id, user_id) values (1, 1);
insert into role_users (role_id, user_id) values (2, 2);
insert into role_users (role_id, user_id) values (3, 3);

insert into group_users (group_id, user_id) values (1, 1);
insert into group_users (group_id, user_id) values (2, 3);
insert into group_users (group_id, user_id) values (3, 2);

insert into bank_accounts (user_id, account_number, account_holder_name, bank_name, branch_name, account_type, status) values (1, '1234567890', 'John Admin', 'Bank A', 'Branch X', 'Savings', true);
insert into bank_accounts (user_id, account_number, account_holder_name, bank_name, branch_name, account_type, status) values (2, '2345678901', 'Sara Manager', 'Bank B', 'Branch Y', 'Checking', true);
insert into bank_accounts (user_id, account_number, account_holder_name, bank_name, branch_name, account_type, status) values (3, '3456789012', 'Mike User', 'Bank C', 'Branch Z', 'Savings', true);

