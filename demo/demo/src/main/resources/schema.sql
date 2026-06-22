drop table if exists bank_accounts;
drop table if exists role_users;
drop table if exists group_users;
drop table if exists groups;
drop table if exists users;
drop table if exists roles;

create table roles (
    id bigint not null auto_increment primary key,
    name varchar(255) not null unique
);

create table users (
    id bigint not null auto_increment primary key,
    username varchar(255),
    email varchar(255) unique,
    password varchar(255),
    role varchar(255),
    telephone varchar(20),
    status boolean default true
);

create table groups (
    id bigint not null auto_increment primary key,
    name varchar(255) unique,
    description varchar(255)
);

create table role_users (
    role_id bigint not null,
    user_id bigint not null,
    primary key (role_id, user_id),
    foreign key (role_id) references roles(id) on delete cascade,
    foreign key (user_id) references users(id) on delete cascade
);

create table group_users (
    group_id bigint not null,
    user_id bigint not null,
    primary key (group_id, user_id),
    foreign key (group_id) references groups(id) on delete cascade,
    foreign key (user_id) references users(id) on delete cascade
);

create table bank_accounts (
    id bigint not null auto_increment primary key,
    user_id bigint not null,
    account_number bigint unique,
    account_holder_name varchar(255),
    bank_name varchar(255),
    branch_name varchar(255),
    account_type varchar(50),
    status boolean default true,
    foreign key (user_id) references users(id) on delete cascade
);
